package com.example.lab1.labthuyettrinh.bottomNavigation4_3

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lab1.Destination


sealed class BottomNavigationItemInfo(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int,
    val route: Destination
) {
    data object Home : BottomNavigationItemInfo("Home", Icons.Filled.Home, Icons.Outlined.Home, 0, Destination.Home)
    data object Shopping : BottomNavigationItemInfo("Shopping", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart, 0, Destination.Shopping)
    data object Create : BottomNavigationItemInfo("Create", Icons.Filled.AddCircle, Icons.Outlined.AddCircle, 0, Destination.Create)
    data object Notification : BottomNavigationItemInfo("Notification", Icons.Filled.Notifications, Icons.Outlined.Notifications, 8, Destination.Notification) // Ví dụ có badge
    data object Account : BottomNavigationItemInfo("Account", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, 0, Destination.Account)
}