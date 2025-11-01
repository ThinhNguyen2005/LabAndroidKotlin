package com.example.lab1.tuan1

import PracticeScreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ThucHanh1() {
    var inputName by remember { mutableStateOf(TextFieldValue("")) }
    var inputAge by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf<String?>(null) }





            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally//ngang
            ) {
                Text(
                    text = "THỰC HÀNH 01",
                    modifier = Modifier.padding(top = 60.dp, bottom = 32.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold

                )
                // Hàng "Họ và tên"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Họ và tên",
                        modifier = Modifier.width(80.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    OutlinedTextField(
                        value = inputName,
                        onValueChange = { newValue ->
                            inputName = newValue
                            errorMessage = null
                        },
                        placeholder = { Text("Nhập tên của bạn") },

                        shape = RoundedCornerShape(8.dp),//bo tròn
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tuổi",
                        modifier = Modifier.width(80.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    OutlinedTextField(
                        value = inputAge,
                        onValueChange = { newValue ->
                            inputAge = newValue
                            errorMessage = null
                        },
                        placeholder = { Text("Nhập tuổi của bạn") },

                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,//Khi người dùng không tương tác (không bấm vào) ô nhập liệu, đường viền của nó sẽ trong suốt (tàng hình).
                            focusedBorderColor = MaterialTheme.colorScheme.primary,//Khi người dùng bấm vào và đang nhập liệu trong ô này, đường viền sẽ chuyển sang màu primary
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        val input = inputAge.text.trim()
                        if (input.isEmpty() || !input.all { it.isDigit() }) {
                            errorMessage = "Dữ liệu của bạn nhập không hợp lệ."
                        } else {
                            val age = input.toInt()
                            when {
                                age in 2..6 -> errorMessage = "Bạn tên ${inputName.text.trim()}, ${inputAge.text.trim()} tuổi, là Trẻ em."
                                age >= 65 -> errorMessage = "Bạn tên ${inputName.text.trim()}, ${inputAge.text.trim()} tuổi, là Người già."
                                age in 7..64 -> errorMessage = "Bạn tên ${inputName.text.trim()}, ${inputAge.text.trim()} tuổi, là Người lớn."
                                age < 2 -> errorMessage = "Bạn tên ${inputName.text.trim()}, ${inputAge.text.trim()} tuổi, là Em bé."
                                else -> errorMessage = null
                            }
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp)
                        .height(50.dp)
                ) {
                    Text("Kiểm tra")
                }

                // Hiển thị thông báo lỗi (nếu có)
                errorMessage?.let { msg ->
                    Text(
                        text = msg,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }
            }

    }

@Preview(showBackground = true)
@Composable
fun ThucHanh1Preview(){
    ThucHanh1()
}