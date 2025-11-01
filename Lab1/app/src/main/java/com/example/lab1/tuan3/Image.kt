package com.example.lab1.tuan3

import android.graphics.Color.rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.R

@Composable
fun ViewImage(
    onBackClicked: () -> Unit = {}

){
    val uriHandler = LocalUriHandler.current
    val url = "https://ut.edu.vn/"
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

        ){
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(rgb(70, 142, 200))

                )
            }
            Text(
                text ="Text Detail",
                modifier = Modifier
                    .padding(start = 70.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(rgb(70, 142, 200))

            )
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = R.drawable.truong),
                contentDescription = "Ảnh đại diện"


            )

            Text(
                text = "Đại học Giao Thông Vận Tải",
                color = Color.Blue,
                fontSize = 20.sp,
//            textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        uriHandler.openUri(url)
                    }
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = R.drawable.truong1),
                contentDescription = "Ảnh đại diện"

            )
            Text(
                text = "in app",
                modifier = Modifier
                    .padding(16.dp)

            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun ViewImageScreenPreview() { // Đổi tên cho rõ ràng
    // Bọc trong Theme của bạn (ví dụ Lab1Theme, MaterialTheme,...)
    MaterialTheme {
        ViewImage()
//        TextDetailScreen() // Chỉ gọi duy nhất hàm này
    }
}