package com.example.lab1.tuan3

import android.graphics.Color.rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.R
import com.example.lab1.TextDetail

@Composable
fun TextField(
    onBackClicked: () -> Unit = {}

){
    var inputNow by remember { mutableStateOf(TextFieldValue("")) }
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
                text = "Text Field",
                modifier = Modifier
                    .padding(start = 70.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(rgb(70, 142, 200))

            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = inputNow,
                onValueChange = { newValue ->
                    inputNow = newValue
                },
                placeholder = { Text("Nhập thông tin") },

            )
            Text(
                text = if(inputNow.text.isEmpty()) "Chưa nhập thông tin" else "Chuỗi bạn nhập là: ${inputNow.text}",
                modifier = Modifier.padding(top = 16.dp),
                color = Color.Red
            )
        }
    }

}


@Preview
@Composable
fun TextFieldPreview() {
    MaterialTheme {
        TextField()
    }
}