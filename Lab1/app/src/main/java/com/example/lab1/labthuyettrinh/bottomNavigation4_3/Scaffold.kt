package com.example.lab1.labthuyettrinh.bottomNavigation4_3

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab1.Destination
import com.example.lab1.labthuyettrinh.navigationDrawer4_2.ContentScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavigationItems = listOf(
        BottomNavigationItemInfo.Home,
        BottomNavigationItemInfo.Shopping,
        BottomNavigationItemInfo.Create,
        BottomNavigationItemInfo.Notification,
        BottomNavigationItemInfo.Account
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Bottom Navigation") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                bottomNavigationItems.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route::class.qualifiedName } == true

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(item.label) },
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
                                    contentDescription = item.label
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Destination.Home
        ) {
            composable<Destination.Home> { ContentScreen(Destination.Home.label) }
            composable<Destination.Shopping> { ContentScreen(Destination.Shopping.label) }
            composable<Destination.Notification> { ContentScreen(Destination.Notification.label) }
            composable<Destination.Create> { ContentScreen(Destination.Create.label) }
            composable<Destination.Account> { ContentScreen(Destination.Account.label) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationAppPreview() {
    BottomNavigationApp()
}