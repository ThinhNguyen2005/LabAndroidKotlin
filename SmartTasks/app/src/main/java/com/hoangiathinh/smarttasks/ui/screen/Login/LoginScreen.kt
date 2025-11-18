package com.hoangiathinh.smarttasks.ui.screen.Login

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoangiathinh.smarttasks.model.AuthViewModel
import com.hoangiathinh.smarttasks.ui.util.ButtonComponent
import com.hoangiathinh.smarttasks.ui.util.GoogleSignInButton
import com.hoangiathinh.smarttasks.ui.util.LogoUTH
import com.hoangiathinh.smarttasks.ui.util.TextInputComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = viewModel(),
    onNavigateToProfile: () -> Unit,
    onNavigateToRegister: () -> Unit = {}
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val uiState by authViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.isLoginSuccess) {
        if (uiState.isLoginSuccess) onNavigateToProfile()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            authViewModel.signInWithIntent(result.data!!)
        } else if (result.resultCode != Activity.RESULT_CANCELED) {
            Toast.makeText(context, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") }
            )
        },
        snackbarHost = { SnackbarHost(SnackbarHostState()) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // ✅ SỬ DỤNG innerPadding
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoUTH()

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Đăng nhập", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "Nhập email và mật khẩu hoặc dùng Google",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextInputComponent(
                value = uiState.emailInput,
                onValueChange = { authViewModel.onEmailChange(it) },
                placeholder = "Email",
                leadingIcon = Icons.Outlined.Email,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextInputComponent(
                value = uiState.passwordInput,
                onValueChange = { authViewModel.onPasswordChange(it) },
                placeholder = "Password",
                leadingIcon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                isPasswordToggleDisplayed = true,
                isPasswordVisible = remember { mutableStateOf(false) }
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                text = "Đăng nhập",
                onClick = { authViewModel.signInWithEmailAndPassword() },
                isLoading = uiState.isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoogleSignInButton(
                onClick = {
                    scope.launch {
                        val intentSender = authViewModel.getGoogleSignInIntentSender()
                        if (intentSender != null) {
                            val request = IntentSenderRequest.Builder(intentSender).build()
                            launcher.launch(request)
                        } else {
                            Toast.makeText(
                                context,
                                "Không thể khởi tạo Google Sign-In",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                isLoading = uiState.isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(
                text = "Chưa có tài khoản? Đăng ký",
                onClick = onNavigateToRegister,
                isLoading = false
            )
        }

        if (uiState.errorMessage != null) {
            LaunchedEffect(uiState.errorMessage) {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
                authViewModel.clearErrorMessage()
            }
        }
    }
}
