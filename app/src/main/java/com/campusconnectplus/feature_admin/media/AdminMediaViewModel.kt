package com.campusconnectplus.feature_admin.media

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusconnectplus.data.repository.Media
import com.campusconnectplus.data.repository.MediaRepository
import com.campusconnectplus.data.repository.MediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class AdminMediaViewModel(
    private val appContext: Context,
    private val repo: MediaRepository
) : ViewModel() {

    private val eventId = MutableStateFlow<String?>(null)

    val media: StateFlow<List<Media>> =
        eventId.flatMapLatest { id ->
            if (id == null) repo.observeMedia()
            else repo.ofEvent(id)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val totalSizeMb: Int get() = media.value.sumOf { it.sizeMb }

    fun setEvent(eventId: String?) { this.eventId.value = eventId }

    fun upsert(media: Media) {
        viewModelScope.launch { repo.upsert(media) }
    }

    fun uploadMedia(uri: Uri, title: String, type: MediaType) {
        viewModelScope.launch {
            val id = System.currentTimeMillis()
            val copied = withContext(Dispatchers.IO) {
                copyPickedMediaToInternalStorage(uri, id, type)
            }
            if (copied == null) {
                Log.e(TAG, "Could not read picked media; check storage/photo permissions.")
                return@launch
            }
            val (fileUri, fileName, bytes) = copied
            val sizeMb = (bytes / (1024 * 1024)).toInt().coerceAtLeast(1)
            val newMedia = Media(
                id = id,
                eventId = 0L,
                url = fileUri,
                type = type,
                title = title,
                fileName = fileName,
                date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date()),
                sizeMb = sizeMb
            )
            repo.upsert(newMedia)
        }
    }

    /**
     * Picker [content://] URIs are not durable. Copy into app files dir so Room/Coil/ExoPlayer keep working.
     */
    private fun copyPickedMediaToInternalStorage(
        source: Uri,
        id: Long,
        type: MediaType
    ): Triple<String, String, Long>? {
        val cr = appContext.contentResolver
        val mime = cr.getType(source)
        val ext = extensionForMime(mime, type)
        val dir = File(appContext.filesDir, UPLOAD_DIR).apply { mkdirs() }
        val outFile = File(dir, "$id.$ext")
        return try {
            cr.openInputStream(source)?.use { input ->
                outFile.outputStream().use { output -> input.copyTo(output) }
            } ?: return null
            val bytes = outFile.length()
            if (bytes == 0L) {
                outFile.delete()
                return null
            }
            Triple(outFile.toURI().toString(), outFile.name, bytes)
        } catch (e: Exception) {
            Log.e(TAG, "copy failed", e)
            outFile.delete()
            null
        }
    }

    private fun extensionForMime(mime: String?, type: MediaType): String {
        return when {
            mime?.contains("png", ignoreCase = true) == true -> "png"
            mime?.contains("webp", ignoreCase = true) == true -> "webp"
            mime?.contains("gif", ignoreCase = true) == true -> "gif"
            mime?.contains("jpeg", ignoreCase = true) == true || mime?.contains("jpg", ignoreCase = true) == true -> "jpg"
            mime?.contains("webm", ignoreCase = true) == true -> "webm"
            mime?.contains("3gpp", ignoreCase = true) == true -> "3gp"
            mime?.contains("video", ignoreCase = true) == true -> "mp4"
            type == MediaType.VIDEO -> "mp4"
            else -> "jpg"
        }
    }

    companion object {
        private const val TAG = "AdminMediaViewModel"
        private const val UPLOAD_DIR = "uploaded_media"
    }

    fun delete(id: String) {
        viewModelScope.launch { repo.delete(id) }
    }
}
