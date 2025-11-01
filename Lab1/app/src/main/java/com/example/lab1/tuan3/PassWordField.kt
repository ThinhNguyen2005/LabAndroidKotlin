package com.example.lab1.tuan3

import android.graphics.Color.rgb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.R

@Composable
fun EnterPassWord(
    onBackClicked: () -> Unit = {},
){
    var passwordInput by remember { mutableStateOf(TextFieldValue("")) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var validationMessage by remember { mutableStateOf<String?>(null) }
    var messageColor by remember { mutableStateOf(Color.Red) }
    fun isPasswordValid(text: String): Boolean {
        return text.length >= 8 && text.any { it.isUpperCase() } && text.any { it.isDigit() }
    }
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
                text = "Text Detail",
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
                modifier = Modifier.fillMaxWidth(),
                value = passwordInput,
                onValueChange = {
                    passwordInput = it
                    validationMessage = null // Xóa thông báo khi người dùng bắt đầu nhập lại
                },
                label = { Text("Mật khẩu") },
                placeholder = { Text("Nhập mật khẩu của bạn") },
                singleLine = true,
            )

            Button(
                onClick = {
                    if (passwordInput.text.isEmpty()) {
                        messageColor = Color.Red
                        validationMessage = "Mật khẩu không được để trống."
                    } else if (isPasswordValid(passwordInput.text)) {
                        messageColor = Color(0xFF006400) // Màu xanh lá cây đậm
                        // SỬA LỖI 3: Sửa lại nội dung thông báo cho đúng
                        validationMessage = "Mật khẩu hợp lệ!"
                    } else {
                        messageColor = Color.Red
                        validationMessage = "Mật khẩu phải dài ít nhất 8 ký tự, có chữ hoa và số."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kiểm tra")
            }
            Spacer(modifier = Modifier.height(24.dp))
            validationMessage?.let { message ->
                Text(
                    text = message,
                    color = messageColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}@Preview(showBackground = true)
@Composable
fun EnterPasswordScreenPreview() {
    MaterialTheme {
        EnterPassWord()
    }
}