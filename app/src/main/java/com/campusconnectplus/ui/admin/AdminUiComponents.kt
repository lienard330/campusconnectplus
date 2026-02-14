package com.campusconnectplus.ui.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(title: String, subtitle: String, onPrimary: (() -> Unit)? = null) {
    Surface(color = Color(0xFF0B2A6B)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text("CampusConnect+", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Admin Portal", color = Color.White.copy(alpha = 0.85f), style = MaterialTheme.typography.labelMedium)
                Spacer(Modifier.height(10.dp))
                Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
                Text(subtitle, color = Color.White.copy(alpha = 0.85f), style = MaterialTheme.typography.labelSmall)
            }

            if (onPrimary != null) {
                Button(
                    onClick = onPrimary,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Outlined.Add, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Create")
                }
            }
        }
    }
}

@Composable
fun EmptyAdminPanel(iconText: String, title: String, hint: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(iconText, style = MaterialTheme.typography.headlineMedium)
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(hint, color = Color(0xFF64748B), style = MaterialTheme.typography.bodySmall)
        }
    }
}
