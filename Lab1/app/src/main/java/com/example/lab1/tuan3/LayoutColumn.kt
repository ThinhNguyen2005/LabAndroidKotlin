package com.example.lab1.tuan3


import android.graphics.Color.rgb
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.R

// Màu sắc để sử dụng
private val lightBlue = Color(0xFFD0E6FF)
private val mediumBlue = Color(0xFF74A6F3)
private val darkBlue = Color(0xFF3B82F6)

@Composable
fun ColumnLayoutScreen(onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding( top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,//ngang


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(rgb(70, 142, 200))

                )
            }
            Text(
                text = "Column Layout",
                modifier = Modifier
                    .padding(start = 70.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(rgb(70, 142, 200))

            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Khoảng cách giữa các Column
        ) {
            repeat(3) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ColorBox(color = lightBlue)
                    ColorBox(color = darkBlue)
                    ColorBox(color = lightBlue)
                    ColorBox(color = darkBlue)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ColumnLayoutScreenScreenPreview() {
    MaterialTheme {
        ColumnLayoutScreen(onBackClicked = {})
    }
}