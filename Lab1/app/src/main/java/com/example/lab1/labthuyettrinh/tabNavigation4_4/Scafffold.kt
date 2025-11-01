package com.example.lab1.labthuyettrinh.tabNavigation4_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.lab1.R
import com.example.lab1.labthuyettrinh.navigationDrawer4_2.ContentScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabNavigationApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // 🔹 Danh sách các mục hiển thị trên TabRow
    val tabItems = listOf(
        TabDestination.Notication,
        TabDestination.CS1,
        TabDestination.CS2,
        TabDestination.CS3, )

    var tabIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    modifier = Modifier.statusBarsPadding(),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )

                PrimaryScrollableTabRow(selectedTabIndex = tabIndex, edgePadding = 0.dp) {
                    tabItems.forEachIndexed { index, item ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.route == item.route
                        } == true
                        if (isSelected) tabIndex = index

                        Tab(
                            selected = isSelected,
                            text = { Text(item.label) },
                            onClick = {
                                if (!isSelected) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TabDestination.Notication.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(TabDestination.Notication.route) { ContentScreen(TabDestination.Notication.label) }
            composable(TabDestination.CS1.route) { ContentScreen(TabDestination.CS1.label) }
            composable(TabDestination.CS2.route) { ContentScreen(TabDestination.CS2.label) }
            composable(TabDestination.CS3.route) { ContentScreen(TabDestination.CS3.label) }
        }

    }
}
@Preview
@Composable
fun TabNavigationAppPreview() {
    TabNavigationApp()
}

