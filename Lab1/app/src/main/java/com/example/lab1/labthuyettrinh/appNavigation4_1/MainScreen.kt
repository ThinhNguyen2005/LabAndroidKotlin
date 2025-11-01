package com.example.lab1.labthuyettrinh.appNavigation4_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySportScreen(
    onNavigateToFootball: () -> Unit = {},
    onNavigateToHockey: () -> Unit = {},
    onNavigateToBaseball: () -> Unit = {},
    onNavigateToMusic: () -> Unit = {},


    ){

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                onClick = onNavigateToFootball,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( bottom = 20.dp),
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "Football",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }
            }
            Card(
                onClick = onNavigateToHockey,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( bottom = 20.dp),

            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "Hockey",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }
            }
            Card(
                onClick = onNavigateToBaseball,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( bottom = 20.dp),

            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "Baseball",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }
            }
            Card(
                onClick = onNavigateToMusic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( bottom = 20.dp),

                ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "Music",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }

}

//
//
//
//@Composable
//fun ProfileScreen(){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//
//    ){
//        Text("BaseballScreen", fontSize = 28.sp)
//
//    }}
//
//@Composable
//fun CalendarScreen(){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//
//    ){
//        Text("BaseballScreen", fontSize = 28.sp)
//
//    }
//}
//@Composable
//fun HomeScreen(){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//
//    ){
//        Text("BaseballScreen", fontSize = 28.sp)
//
//    }
//}