package com.example.lab1.tuan3


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.R

@Composable
fun Tuan3_lab2off(
    onReady: () -> Unit = {}

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,//ngang
        verticalArrangement = Arrangement.Center // Căn giữa cho đẹp hơn

    ) {


        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Ảnh đại diện"

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Jetpack Compose",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,

        )
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(120.dp))

        Button(
            onClick = onReady,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text("I'm ready")
        }
    }

}
@Preview(showBackground = true)
@Composable
fun Tuan3_lab2offPreview() {
    MaterialTheme {
        Tuan3_lab2off()
    }
}
