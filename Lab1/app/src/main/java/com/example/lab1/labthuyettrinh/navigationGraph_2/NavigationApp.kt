package com.example.lab1.labthuyettrinh.navigationGraph_2


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
data object Home
@Serializable
data object Detail

@Composable
fun NavigationAppDetail(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
        builder = {
            composable<Home>{
                HomeScreen(navController)
            }
            composable<Detail>{
                DetailScreen(navController)
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Màn hình chính",
                    modifier = Modifier
                        .padding(start = 100.dp)
                ) },
                navigationIcon = {

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = { navController.navigate(Detail) },
            ) {
                Text(text = "Go to Detail Screen", fontSize = 22.sp)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Màn hình chi tiết", modifier = Modifier. padding(start = 100.dp)) },
                navigationIcon = {

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        ) {
            Text(
                text = "Detail Screen", fontSize = 28.sp,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = { navController.navigate(Home) },
            ) {
                Text(text = "Go to Home Screen", fontSize = 22.sp)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun NavigationAppPreview() {
    NavigationAppDetail()
}