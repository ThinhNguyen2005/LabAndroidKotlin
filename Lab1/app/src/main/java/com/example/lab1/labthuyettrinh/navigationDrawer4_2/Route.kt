package com.example.lab1.labthuyettrinh.navigationDrawer4_2

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.* // Import tất cả icon Filled
import androidx.compose.material.icons.outlined.* // Import tất cả icon Outlined
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lab1.Destination

// @Serializable

sealed class NavigationDrawerItemInfo(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Destination
) {
    data object Home : NavigationDrawerItemInfo("Home", Icons.Filled.Home, Icons.Outlined.Home, Destination.Home)
    data object Shopping : NavigationDrawerItemInfo("Shopping", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart, Destination.Shopping)
    data object Create : NavigationDrawerItemInfo("Create", Icons.Filled.AddCircle, Icons.Outlined.AddCircle, Destination.Create)
    data object Notification : NavigationDrawerItemInfo("Notification", Icons.Filled.Notifications, Icons.Outlined.Notifications, Destination.Notification)
    data object Account : NavigationDrawerItemInfo("Account", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, Destination.Account)
}