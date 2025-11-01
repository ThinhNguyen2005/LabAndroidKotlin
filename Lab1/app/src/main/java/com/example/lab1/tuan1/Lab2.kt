package com.example.lab1.tuan1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThucHanh2Home(){
    var emailPublic by remember { mutableStateOf(TextFieldValue("")) }
    var checkmail by remember { mutableStateOf<String?>(null) }

    fun checkEmail(text: String): Boolean {
        return text.contains("@") && text.contains(".")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Thực hành 02",
            modifier = Modifier.padding(top = 60.dp, bottom = 32.dp),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = emailPublic,
            onValueChange = { newValue ->
                emailPublic = newValue
                checkmail = null
            },
            placeholder = { Text("Nhập vào email của bạn") },

            singleLine = true,
            modifier = Modifier
                .width(300.dp)
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (checkEmail(emailPublic.text) && emailPublic.text.isNotEmpty()) {
                    checkmail = "Email hợp lệ"
                } else {
                    checkmail = "Email không hợp lệ"
                }
            }
        ) {
            Text("Kiểm tra")
        }

        Spacer(modifier = Modifier.height(24.dp))

        checkmail?.let { msg ->
            Text(
                text = msg,
                color = if (msg == "Email hợp lệ") Color.Green else Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Lab2PreView(){
    ThucHanh2Home()
}
