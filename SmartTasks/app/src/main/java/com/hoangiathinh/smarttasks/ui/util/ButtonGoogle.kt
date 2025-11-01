package com.hoangiathinh.smarttasks.ui.util

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hoangiathinh.smarttasks.R

/**
 * Nút "Sign in with Google" đã được cải tiến:
 * - Sử dụng OutlinedButton của Material 3 để có hiệu ứng ripple và ngữ nghĩa tốt hơn.
 * - Hỗ trợ tốt hơn cho Chế độ tối (Dark Mode) bằng cách sử dụng màu từ MaterialTheme.colorScheme.
 * - Cải thiện trạng thái tải (loading state) để không làm thay đổi layout.
 * - Tuân thủ tốt hơn các nguyên tắc về thương hiệu của Google.
 */
@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    text: String = "Sign in with Google",
    loadingText: String = "Signing in...",
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        enabled = !isLoading
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Sử dụng Crossfade để chuyển đổi mượt mà giữa icon và thanh tiến trình
            Crossfade(targetState = isLoading, label = "IconVsSpinner") { loading ->
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google_logo),
                        contentDescription = "Google Logo",
                        tint = Color.Unspecified, // Không áp dụng tint để giữ màu gốc của logo
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = if (isLoading) loadingText else text,
                style = MaterialTheme.typography.labelLarge,
                // Không cần đặt màu ở đây vì contentColor của Button đã xử lý
            )
        }
    }
}

/**
 * Màn hình đăng nhập đã được cải tiến:
 * - Cung cấp thêm ngữ cảnh cho người dùng với logo ứng dụng, tiêu đề và mô tả.
 * - Bố cục được căn giữa, thân thiện và hấp dẫn hơn.
 */
@Composable
fun SignInScreen(
    isLoading: Boolean = false,
    onGoogleSignInClick: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Thay R.drawable.app_logo bằng logo của ứng dụng bạn
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome to SmartTasks", // Thay bằng tên ứng dụng của bạn
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sign in to sync your tasks and access them anywhere.", // Thay bằng thông điệp của bạn
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            // Đẩy nút đăng nhập xuống phía dưới màn hình
            Spacer(modifier = Modifier.weight(1f))

            GoogleSignInButton(
                isLoading = isLoading,
                onClick = onGoogleSignInClick
            )
        }
    }
}


@Preview(name = "Sign-In Screen - Default", showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(isLoading = false)
}

@Preview(name = "Sign-In Screen - Loading", showBackground = true)
@Composable
fun SignInScreenLoadingPreview() {
    SignInScreen(isLoading = true)
}