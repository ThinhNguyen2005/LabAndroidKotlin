package com.example.lab1.labthuyettrinh.appNavigation4_1

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab1.Destination
import com.example.lab1.labthuyettrinh.navigationDrawer4_2.ContentScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppNavigationSport() {
    val navController = rememberNavController()
    // Lấy route hiện tại từ NavController để cập nhật UI
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topLevelDestinations = BottomNavigationSport.items.map { it.route.route }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val title = when (currentRoute) {
                        Destination.Home.route -> "Home"
                        Destination.Calendar.route -> "Calendar"
                        Destination.MySport.route -> "My Sport"
                        Destination.Profile.route -> "Profile"
                        Destination.Football.route -> "Football"
                        Destination.Hockey.route -> "Hockey"
                        Destination.Baseball.route -> "Baseball"
                        Destination.Music.route -> "Music"
                        else -> "My Sport"
                    }
                    Text(title)
                },
                navigationIcon = {
                    if (currentRoute !in topLevelDestinations) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = { HomeBottomNavigation(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destination.MySport.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Destination.MySport.route) {
                MySportScreen(
                    onNavigateToFootball = { navController.navigate(Destination.Football.route) },
                    onNavigateToHockey = { navController.navigate(Destination.Hockey.route) },
                    onNavigateToBaseball = { navController.navigate(Destination.Baseball.route) },
                    onNavigateToMusic = { navController.navigate(Destination.Music.route) }
                )
            }

            composable(route = Destination.Football.route) {
                ContentScreen(Destination.Football.label)
            }
            composable(route = Destination.Hockey.route) {
                ContentScreen(Destination.Hockey.label)
            }
            composable(route = Destination.Baseball.route) {
                ContentScreen(Destination.Baseball.label)
            }
            composable(route = Destination.Music.route) {
                ContentScreen(Destination.Music.label)
            }
            composable(route = Destination.Home.route) {
                ContentScreen(Destination.Home.label)
            }
            composable(route = Destination.Calendar.route) {
                ContentScreen(Destination.Calendar.label)
            }
            composable(route = Destination.Profile.route) {
                ContentScreen(Destination.Profile.label)
            }

        }
    }
}

@Composable
fun HomeBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .height(70.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        BottomNavigationSport.items.forEach { item ->
            val route = item.route.route
            val isSelected = currentRoute == route

            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount > 0) {
                                Badge { Text(item.badgeCount.toString()) }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.route.toString(),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                selected = isSelected,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.DarkGray,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppNavigationSportPreview() {
    MyAppNavigationSport()
}



























//package com.example.lab1.labthuyettrinh.appNavigation
//
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CalendarMonth
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.outlined.AccountCircle
//import androidx.compose.material.icons.outlined.CalendarMonth
//import androidx.compose.material.icons.outlined.Home
//import androidx.compose.material.icons.outlined.Star
//import androidx.compose.material.icons.rounded.AccountCircle
//import androidx.compose.material.icons.rounded.Star
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//
//
//object AppRoutes {
//    const val UI_HOME = "ui_home"
//    const val UI_FOOTBALL = "ui_football"
//    const val UI_HOCKEY = "ui_hockey"
//    const val UI_BASEBALL = "ui_baseball"
//    const val UI_SPORTS = "ui_sports"
//
//    const val UI_CALENDER = "ui_calender"
//    const val UI_PROFILE = "ui_profile"
//
//
//
//}
//@OptIn(ExperimentalMaterial3Api::class)
//
//@Composable
//fun MyAppNavigationSport() {
//    val navController = rememberNavController()
//
//    Scaffold(
//
//        topBar = {
//            CenterAlignedTopAppBar(title = { Text("My Sport") })
//        },
//        bottomBar = { HomeBottomNavigation(navController) }
//
//
//    ) {innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = AppRoutes.UI_SPORTS,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(route = AppRoutes.UI_HOME) {
//                HomeScreen(
//
//                )
//            }
//            composable(route = AppRoutes.UI_CALENDER) {
//                CalendarScreen(
//
//                )
//            }
//            composable(route = AppRoutes.UI_PROFILE) {
//                ProfileScreen(
//
//                )
//            }
//            composable(route = AppRoutes.UI_SPORTS) {
//                MySportScreen(
//                    onNavigateToFootball = { navController.navigate(AppRoutes.UI_FOOTBALL) },
//                    onNavigateToHockey = { navController.navigate(AppRoutes.UI_HOCKEY) },
//                    onNavigateToBaseball = { navController.navigate(AppRoutes.UI_BASEBALL) }
//                )
//
//            }
//
//            composable(route = AppRoutes.UI_FOOTBALL) {
//                FootballScreen(onBackClicked = { navController.popBackStack() })
//            }
//            composable(route = AppRoutes.UI_HOCKEY) {
//                HockeyScreen(onBackClicked = { navController.popBackStack() })
//            }
//            composable(route = AppRoutes.UI_BASEBALL) {
//                BaseballScreen(onBackClicked = { navController.popBackStack() })
//            }
//
//        }
//    }
//}
//@Composable
//fun HomeBottomNavigation(navController: NavHostController) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    NavigationBar(
//        modifier = Modifier
//            .padding(horizontal = 8.dp)
//            .height(70.dp),
//        containerColor = MaterialTheme.colorScheme.surface,
//        tonalElevation = 0.dp
//    ) {
//        BottomNavigationSport.items.forEach { item ->
//            val route = item.label // <-- Dùng label làm route
//            val isSelected = currentRoute == route
//
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
//                        contentDescription = item.label,
//                        modifier = Modifier.size(28.dp)
//                    )
//                },
//                selected = isSelected,
//                onClick = {
//                    if (currentRoute != route) {
//                        navController.navigate(route) {
//                            popUpTo(navController.graph.startDestinationId) { saveState = true }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                },
//                alwaysShowLabel = false,
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = Color.DarkGray,
//                    unselectedIconColor = Color.Gray,
//                    indicatorColor = Color.Transparent
//                )
//            )
//        }
//    }
//}
//

