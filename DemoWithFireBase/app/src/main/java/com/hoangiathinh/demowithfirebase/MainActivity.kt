package com.hoangiathinh.demowithfirebase

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult // SỬA LỖI: Import rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp // SỬA LỖI: Import cho dp
import androidx.lifecycle.viewmodel.compose.viewModel // SỬA LỖI: Import cho viewModel()
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.hoangiathinh.demowithfirebase.Auth.GoogleAuthViewModel
import com.hoangiathinh.demowithfirebase.ui.theme.DemoWithFireBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DemoWithFireBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GoogleLoginScreen()
                }
            }
        }
    }
}

@Composable
fun GoogleLoginScreen(viewModel: GoogleAuthViewModel = viewModel()) {
    val context = LocalContext.current
    val activity = context as? Activity

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        if (result.resultCode == Activity.RESULT_OK && data != null) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (task.isSuccessful) {
                val account = task.result
                viewModel.firebaseAuthWithGoogle(account.idToken!!) { success ->
                    if (success) {
                        Toast.makeText(context, "✅ Login success!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "❌ Login failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "❌ Google sign-in task failed!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "❌ Sign-in canceled or failed!", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // lỗi 'dp' đã được sửa bằng cách import
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            if (activity != null) {
                val signInIntent = viewModel.getClient(activity).signInIntent
                launcher.launch(signInIntent)
            }
        }) {
            Text(text = "Sign in with Google")
        }
    }
}