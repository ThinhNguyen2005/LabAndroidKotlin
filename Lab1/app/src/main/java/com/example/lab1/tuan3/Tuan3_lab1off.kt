package com.example.lab1.tuan3


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Tuan3_lab1off(){
    var textColor by remember { mutableStateOf(Color.Black) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,//ngang
    ) {
        // Tiêu đề
        Text(
            text = "Xin chào",
            modifier = Modifier.padding(top = 80.dp, bottom = 24.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "I'm",
        )
        Text(
            text = "Nguyễn Hoàng Gia Thịnh",
            style = MaterialTheme.typography.headlineSmall,
            color = textColor
        )
        Spacer(modifier = Modifier.height(80.dp))

        Button(
            onClick = {
                textColor = Color.Red
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .height(50.dp)
        ) {
            Text("Say hi!")
        }

    }
}
@Preview(showBackground = true)
@Composable
fun Tuan3_lab1offPreview() {
    MaterialTheme {
        Tuan3_lab1off()
    }
}