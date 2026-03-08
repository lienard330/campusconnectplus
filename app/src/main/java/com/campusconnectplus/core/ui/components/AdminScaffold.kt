package com.campusconnectplus.core.ui.components

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.campusconnectplus.ui.admin.AdminBottomBar
import com.campusconnectplus.ui.admin.AdminTab
import com.campusconnectplus.ui.admin.tabsForRole

@Composable
fun AdminScaffold(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    allowedTabs: List<AdminTab>,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = { AdminBottomBar(currentRoute = currentRoute, onNavigate = onNavigate, tabs = allowedTabs) }
    ) { _ ->
        content()
    }
}
