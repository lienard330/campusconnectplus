package com.campusconnectplus.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AdminSettingsScreen(onLogout: () -> Unit = {}) {
    var autoSync by remember { mutableStateOf(true) }
    var offlineMode by remember { mutableStateOf(false) }
    var notifications by remember { mutableStateOf(true) }
    var compactView by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        TopBar(title = "System Settings", subtitle = "Configure app preferences and data")

        Spacer(Modifier.height(10.dp))

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("App Preferences", fontWeight = FontWeight.SemiBold)
                    ToggleRow("Auto Sync", "Automatically sync data when online", autoSync) { autoSync = it }
                    ToggleRow("Offline Mode", "Enable full offline functionality", offlineMode) { offlineMode = it }
                    ToggleRow("Notifications", "Show system notifications", notifications) { notifications = it }
                    ToggleRow("Compact View", "Use condensed layout for lists", compactView) { compactView = it }
                }
            }

            Card(shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Local Storage", fontWeight = FontWeight.SemiBold)
                    Text("Storage Usage", color = Color(0xFF64748B), style = MaterialTheme.typography.labelMedium)
                    LinearProgressIndicator(progress = { 0f }, modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        StorageStat("Events", "1")
                        StorageStat("Media Files", "1")
                        StorageStat("Announcements", "1")
                    }
                }
            }

            Card(shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Data Management", fontWeight = FontWeight.SemiBold)
                    OutlinedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Export Data Backup") }
                    OutlinedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Refresh Storage Stats") }
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Reset All Data") }
                }
            }

            Card(shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("System Information", fontWeight = FontWeight.SemiBold)
                    InfoRow("App Version", "1.0.2")
                    InfoRow("Build Date", "Feb 11, 2026")
                    InfoRow("Database", "Local Storage")
                    InfoRow("Status", "Operational", valueColor = Color(0xFF16A34A))
                }
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7ED))
            ) {
                Column(Modifier.padding(14.dp)) {
                    Text("Local Data Storage", fontWeight = FontWeight.SemiBold, color = Color(0xFF9A3412))
                    Text(
                        "All data is stored locally on this device. Regular backups are recommended to prevent data loss.",
                        color = Color(0xFF9A3412),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0B2A6B))
            ) {
                Icon(Icons.Outlined.Logout, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Log out")
            }
        }
    }
}

@Composable
private fun ToggleRow(title: String, subtitle: String, checked: Boolean, onChecked: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium)
            Text(subtitle, color = Color(0xFF64748B), style = MaterialTheme.typography.labelSmall)
        }
        Switch(checked = checked, onCheckedChange = onChecked)
    }
}

@Composable
private fun StorageStat(label: String, value: String) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold)
        Text(label, color = Color(0xFF64748B), style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun InfoRow(label: String, value: String, valueColor: Color = Color(0xFF0F172A)) {
    Row(Modifier.fillMaxWidth()) {
        Text(label, modifier = Modifier.weight(1f), color = Color(0xFF64748B))
        Text(value, color = valueColor, fontWeight = FontWeight.Medium)
    }
}
