package com.example.lab1

import MyAppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lab1.labthuyettrinh.appNavigation4_1.MyAppNavigationSport


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Gọi hàm điều hướng chính của ứng dụng
//                    MyAppNavigation()
//                    NavigationDrawerApp()
//                    BottomNavigationApp()
//                    TabNavigationApp()
//                    AppNavigation()
//                    MyAppNavigation()
//                    NavigationAppDetail()
                    MyAppNavigationSport()
                }
            }
        }
    }
}
