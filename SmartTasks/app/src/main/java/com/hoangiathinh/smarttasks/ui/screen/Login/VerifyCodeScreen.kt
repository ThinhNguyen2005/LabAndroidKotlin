//package com.hoangiathinh.smarttasks.ui.screen
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.hoangiathinh.smarttasks.model.AuthViewModel
//import com.hoangiathinh.smarttasks.model.UserViewModel
//import com.hoangiathinh.smarttasks.ui.util.ButtonComponent
//import com.hoangiathinh.smarttasks.ui.util.LogoUTH
//import com.hoangiathinh.smarttasks.ui.util.VerificationCodeInput
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//
//@Composable
//fun VerificationScreen(
//    authViewModel: AuthViewModel = viewModel(),
//
//    onNext: (String) -> Unit,
//    onBack: () -> Unit
//) {
//    // Trạng thái Scaffold
//    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
//    val uiState by authViewModel.uiState.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("") },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        },
//        snackbarHost = { SnackbarHost(snackbarHostState) }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            LogoUTH()
//
//            Spacer(modifier = Modifier.height(30.dp))
//            Text(
//                text = "Enter your email. We’ll send a verification code to reset your password.",
//                style = MaterialTheme.typography.bodyMedium
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            VerificationCodeInput(
//                value = viewModel.code.value,
//                onValueChange = { newCode ->
//                    viewModel.code.value = newCode
//                }
//            )
//
//            Spacer(modifier = Modifier.height(40.dp))
//
//            ButtonComponent(
//                text = "Next",
//                onClick = {
//                    if (viewModel.code.value.isNotEmpty()) {
//                        onNext(viewModel.code.value)
//                    } else {
//                        scope.launch {
//                            snackbarHostState.showSnackbar("Please enter your email")
//                        }
//                    }
//                }
//            )
//        }
//    }
//
//}