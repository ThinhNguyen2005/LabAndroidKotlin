package com.example.lab1.labthuyettrinh.appNavigation4_1
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lab1.Destination

sealed class BottomNavigationSport(
    val route: Destination,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int = 0,
) {
    data object Home : BottomNavigationSport(Destination.Home, Icons.Filled.Home, Icons.Outlined.Home,0)
    data object Calendar : BottomNavigationSport(Destination.Calendar, Icons.Filled.CalendarMonth, Icons.Outlined.CalendarMonth,0)
    data object MySport : BottomNavigationSport(Destination.MySport, Icons.Filled.Star, Icons.Outlined.Star,0)
    data object Profile : BottomNavigationSport(Destination.Profile, Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle,0)
    companion object {
        val items = listOf(Home, Calendar, MySport, Profile)
    }
}