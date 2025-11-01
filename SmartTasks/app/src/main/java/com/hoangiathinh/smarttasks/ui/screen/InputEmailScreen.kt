package com.hoangiathinh.smarttasks.ui.screen

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoangiathinh.smarttasks.model.AuthViewModel
import kotlinx.coroutines.launch
import com.hoangiathinh.smarttasks.ui.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputEmailScreen(
    authViewModel: AuthViewModel = viewModel(),
    onNext: (String) -> Unit,
    onBack: () -> Unit,
    onNavigateToProfile: () -> Unit = {}
) {
    val uiState by authViewModel.uiState.collectAsState()
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // One Tap launcher for Google sign-in
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            authViewModel.signInWithIntent(result.data!!)
        }
    }

    // Navigate to profile when login success
    LaunchedEffect(key1 = uiState.isLoginSuccess) {
        if (uiState.isLoginSuccess) onNavigateToProfile()
    }

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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LogoUTH()

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Đăng nhập",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Nhập email để tiếp tục hoặc dùng Google",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextInputComponent(
                value = uiState.emailInput,
                onValueChange = { email -> authViewModel.onEmailChange(email) },
                placeholder = "Your Email",
                leadingIcon = Icons.Outlined.Email,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                text = "Đăng nhập",
                onClick = {
                    if (uiState.emailInput.isNotEmpty()) {
                        onNext(uiState.emailInput)
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Please enter your email")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoogleSignInButton(
                onClick = {
                    scope.launch {
                        val intentSender = authViewModel.getGoogleSignInIntentSender()
                        if (intentSender != null) {
                            val request = IntentSenderRequest.Builder(intentSender).build()
                            launcher.launch(request)
                        }
                    }
                },
                isLoading = uiState.isLoading
            )
        }
    }
}
