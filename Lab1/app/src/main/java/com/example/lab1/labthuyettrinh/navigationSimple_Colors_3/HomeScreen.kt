package com.example.lab1.labthuyettrinh.navigationSimple_Colors_3

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lab1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .border(4.dp, Color(0xFFFF0000), shape = RoundedCornerShape(16.dp))
                    // Chuyển sang route dạng chuỗi
                    .clickable { navController.navigate("detail/RED/FFFF0000") }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("RED", color = Color(0xFFFF0000), fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .border(4.dp, Color(0xFF00FF00), shape = RoundedCornerShape(16.dp))
                    // SỬA LỖI ĐIỀU HƯỚNG: Chuyển sang route dạng chuỗi
                    .clickable { navController.navigate("detail/GREEN/FF00FF00") }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Đặt Text "GREEN" vào trong Box
                Text("GREEN", color = Color(0xFF00FF00), fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .border(4.dp, Color(0xFF0000FF), shape = RoundedCornerShape(16.dp))
                    //  Chuyển sang route dạng chuỗi
                    .clickable { navController.navigate("detail/BLUE/FF0000FF") }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("BLUE", color = Color(0xFF0000FF), fontSize = 24.sp)
            }
        }
    }
}

