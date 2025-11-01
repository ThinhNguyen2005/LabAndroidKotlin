package com.hoangiathinh.smarttasks.ui.screen

import com.hoangiathinh.smarttasks.ui.util.TextInputComponent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoangiathinh.smarttasks.model.AuthViewModel
import com.hoangiathinh.smarttasks.ui.util.ButtonComponent
import com.hoangiathinh.smarttasks.ui.util.LogoUTH

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ShowAllScreen(
    authViewModel: AuthViewModel = viewModel(),
//    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by authViewModel.uiState.collectAsState()

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoUTH()

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Confirm",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "We are here to help you!",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextInputComponent(
                value = uiState.emailInput,
                onValueChange = { email -> authViewModel.onEmailChange(email) },
                placeholder = "Your Email",
                leadingIcon = Icons.Outlined.Email,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(10.dp))
//            TextInputComponent(
//                value = uiState.code.value,
//                onValueChange = { viewModel.code.value = it },
//                placeholder = "Your Email",
//                leadingIcon = Icons.Outlined.Email,
//                keyboardType = KeyboardType.Number
//            )
//            Spacer(modifier = Modifier.height(10.dp))

            TextInputComponent(
                value = uiState.passwordInput,
                onValueChange = { password -> authViewModel.onPasswordChange(password) },
                placeholder = "Password",
                leadingIcon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                isPasswordToggleDisplayed = true,
                isPasswordVisible = remember { mutableStateOf(false) }
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                text = "Next",
                onClick = {

                }
            )
        }
    }
}