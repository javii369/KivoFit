package com.KivoFit.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.KivoFit.navigation.Route

private data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem(Route.History.route, Icons.Filled.History, "Historial"),
        BottomNavItem(Route.Calendar.route, Icons.Filled.CalendarToday, "Calendario"),
        BottomNavItem(Route.Inicio.route, Icons.Filled.Home, "Inicio"),
        BottomNavItem(Route.Chat.route, Icons.Filled.Chat, "Chat IA"),
        BottomNavItem(Route.Notifications.route, Icons.Filled.Notifications, "Avisos")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { androidx.compose.material3.Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1
                ) },
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to avoid building up a large stack
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when reselecting the same item
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
