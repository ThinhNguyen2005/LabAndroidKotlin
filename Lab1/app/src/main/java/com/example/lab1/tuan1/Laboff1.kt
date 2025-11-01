import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp

@Composable
fun PracticeScreen() {
    // 1. Quản lý trạng thái đầu vào của TextField
    var inputAmount by remember { mutableStateOf(TextFieldValue("")) }

    // 2. Quản lý trạng thái số lượng nút sẽ tạo (List State)
    var itemCount by remember { mutableStateOf(0) }

    // 3. Quản lý trạng thái thông báo lỗi (Message State)
    var errorMessage by remember { mutableStateOf<String?>(null) } // Null nếu không có lỗi

    // 4. Định nghĩa màu đỏ rực rỡ cho các nút
    val buttonColor = Color(0xFFE53935) // Đỏ (Red 600)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally//ngang
    ) {
        // Tiêu đề
        Text(
            text = "Thực hành 02",
            modifier = Modifier.padding(top = 80.dp, bottom = 24.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        // --- Khu vực Nhập liệu và Nút Tạo ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TextField
            OutlinedTextField(
                value = inputAmount,
                onValueChange = { newValue ->
                    inputAmount = newValue
                    errorMessage = null // Xóa lỗi khi người dùng nhập lại
                },
                placeholder = { Text("Nhập vào số lượng") },

                // Sử dụng singleLine để TextField gọn gàng hơn
                singleLine = true,
                modifier = Modifier
                    .weight(1f) // Chiếm phần lớn không gian
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Button "Tạo"
            Button(
                onClick = {
                    // --- Logic Kiểm tra Đầu vào ---
                    val input = inputAmount.text.trim()

                    if (input.isEmpty() || !input.all { it.isDigit() }) {
                        // Trường hợp 1: Nhập trống hoặc không phải là số
                        errorMessage = "Dữ liệu của bạn nhập không hợp lệ."
                        itemCount = 0 // Đảm bảo danh sách trống
                    } else {
                        // Trường hợp 2: Đầu vào hợp lệ (là số)
                        val count = input.toIntOrNull() ?: 0
                        if (count > 0 && count <= 50) { // Giới hạn số lượng hợp lý
                            itemCount = count
                            errorMessage = null // Xóa lỗi nếu có
                        } else if (count == 0) {
                            errorMessage = "Số lượng phải lớn hơn 0."
                            itemCount = 0
                        } else {
                            errorMessage = "Số lượng quá lớn, vui lòng nhập dưới 50."
                            itemCount = 0
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.height(56.dp)
            ) {
                Text("Tạo")
            }
        }

        // --- Hiển thị Thông báo Lỗi ---
        errorMessage?.let { msg ->
            Text(
                text = msg,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Danh sách Nút Động ---
        // LazyColumn tối ưu cho việc tạo danh sách lớn
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp), // Khoảng cách giữa các nút
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tạo danh sách các item từ 1 đến itemCount
            items((1..itemCount).toList()) { index ->
                Button(
                    onClick = { /* Xử lý sự kiện khi nhấn nút nếu cần */ },
                    // Tùy chỉnh màu nền
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Text(
                        text = "$index",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PracticeScreenPreView(){
    PracticeScreen()
}