package com.example.lab1.labthuyettrinh.navigationDrawer4_2

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.example.lab1.Destination
import com.example.lab1.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigationItems = listOf(
        NavigationDrawerItemInfo.Home,
        NavigationDrawerItemInfo.Shopping,
        NavigationDrawerItemInfo.Create,
        NavigationDrawerItemInfo.Notification,
        NavigationDrawerItemInfo.Account
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.width(256.dp))
                navigationItems.forEach { item ->
                    val isSelected = currentDestination?.route == item.route::class.qualifiedName
                    NavigationDrawerItem(
                        icon = { Icon(if (isSelected) item.selectedIcon else item.unselectedIcon, contentDescription = null) },
                        label = { Text(item.label) },
                        selected = isSelected,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
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
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerAppPreview() {
    NavigationDrawerApp()
}