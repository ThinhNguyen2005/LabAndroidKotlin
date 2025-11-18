package com.hoangiathinh.smarttasks.ui.screen.Login

import androidx.compose.runtime.Composable
import com.hoangiathinh.smarttasks.ui.util.TextInputComponent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoangiathinh.smarttasks.model.AuthViewModel
import kotlinx.coroutines.launch
import com.hoangiathinh.smarttasks.ui.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordScreen(
    authViewModel: AuthViewModel = viewModel(),
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val uiState by authViewModel.uiState.collectAsState()

    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())//cuộn
                .imePadding(), // tự nâng khi bàn phím mở
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LogoUTH()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Create new password",
                style = MaterialTheme.typography.headlineMedium,
            )

            Text(
                text = "Your new password must be different from previous used passwords",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextInputComponent(
                value = uiState.passwordInput,
                onValueChange = { password -> authViewModel.onPasswordChange(password) },
                placeholder = "Password",
                leadingIcon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                isPasswordToggleDisplayed = true,
                isPasswordVisible = remember { mutableStateOf(false) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextInputComponent(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirm Password",
                leadingIcon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                isPasswordToggleDisplayed = true,
                isPasswordVisible = remember { mutableStateOf(false) }
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                text = "Next",
                onClick = {
                    if (uiState.passwordInput.isNotEmpty() && uiState.passwordInput == confirmPassword) {
                        authViewModel.registerWithEmailAndPassword()
                        onNext()
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Nhập 2 mật khẩu giống nhau")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
