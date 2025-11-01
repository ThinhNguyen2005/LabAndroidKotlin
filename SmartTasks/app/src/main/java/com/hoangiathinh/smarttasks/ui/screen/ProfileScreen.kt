package com.hoangiathinh.smarttasks.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState // SỬA: Thêm import
import androidx.compose.runtime.getValue // SỬA: Thêm import
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.hoangiathinh.smarttasks.R
import com.hoangiathinh.smarttasks.model.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    onSignOut: () -> Unit,
    onProductDetail: ()-> Unit
) {
    val uiState by authViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") }
                // Xóa nút back vì đây là màn hình gốc sau khi login
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Box {
                val painter = rememberAsyncImagePainter(
                    // SỬA LỖI 2: Dùng uiState
                    model = uiState.photoUrl,
                    // Đảm bảo bạn có file này trong res/drawable
                    error = painterResource(id = R.drawable.ic_default_avatar)
                )
                Image(
                    painter = painter,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                // Đảm bảo bạn có file này trong res/drawable
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "Edit Picture",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.BottomEnd)
                        .background(Color.White, CircleShape)
                        .padding(4.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // SỬA LỖI 3: Dùng uiState cho các trường
            ProfileTextField(label = "Name", value = uiState.name)
            ProfileTextField(label = "Email", value = uiState.emailInput)
            ProfileTextField(label = "Date of Birth", value = uiState.birthday.ifEmpty { "Chưa cung cấp" })
            ProfileTextField(label = "Phone Number", value = uiState.phoneNumber.ifEmpty { "Chưa cung cấp" })

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onSignOut, // SỬA: Gọi onSignOut
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Sign Out")
            }
            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa 2 nút

            Button(
                onClick = onProductDetail, // Gọi đúng lambda
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "View Product Detail") // Sửa lại text
            }
        }
    }
}

@Composable
fun ProfileTextField(label: String, value: String?) {
    OutlinedTextField(
        value = value ?: "...",
        onValueChange = {},
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        readOnly = true
    )
}