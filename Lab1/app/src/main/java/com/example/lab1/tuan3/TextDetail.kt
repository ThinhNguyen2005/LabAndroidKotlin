package com.example.lab1 // Thay đổi package cho phù hợp với dự án của bạn

import android.graphics.Color.rgb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDetail(
    onBackClicked: () -> Unit = {}
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding( 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                    .padding(start = 50.dp),
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
        ){ val annotatedString = buildAnnotatedString {
            append("The ")
            withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                append("quick")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFC07632), // Màu nâu cam
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(" Brown")
            }

            append(" fox j u m p s ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("over")
            }

            append(" ")

            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("the")
            }
            append(" ")
            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                append("lazy")
            }
            append(" dog.")
        }

            Text(
                text = annotatedString,
                fontSize = 24.sp,
                lineHeight = 40.sp
            ) }

    }

}

@Preview(showBackground = true)
@Composable
fun TextDetailScreenPreview() { // Đổi tên cho rõ ràng
    // Bọc trong Theme của bạn (ví dụ Lab1Theme, MaterialTheme,...)
    MaterialTheme {
        TextDetail()
//        TextDetailScreen() // Chỉ gọi duy nhất hàm này
    }
}