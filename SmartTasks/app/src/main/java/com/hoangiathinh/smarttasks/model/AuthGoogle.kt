// File: com/hoangiathinh/smarttasks/model/AuthGoogle.kt

package com.hoangiathinh.smarttasks.model

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hoangiathinh.smarttasks.R // Đảm bảo import R.string
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

// Định nghĩa một kiểu Result đơn giản
sealed class GoogleSignInResult {
    data class Success(val state: AuthUiState) : GoogleSignInResult()
    data class Error(val message: String) : GoogleSignInResult()
}

class AuthGoogle(
    private val context: Context,
    private val oneTapClient: SignInClient = Identity.getSignInClient(context)
) {

    private val auth = FirebaseAuth.getInstance()

    // 1. Bắt đầu đăng nhập, trả về IntentSender để chạy
    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    // 2. Xử lý kết quả trả về từ Intent
    suspend fun signInWithIntent(intent: Intent): GoogleSignInResult {
        val credential = try {
            oneTapClient.getSignInCredentialFromIntent(intent)
        } catch (e: ApiException) {
            e.printStackTrace()
            return GoogleSignInResult.Error(e.message ?: "Lỗi không xác định")
        }

        val googleIdToken = credential.googleIdToken
        if (googleIdToken == null) {
            return GoogleSignInResult.Error("Không lấy được ID token")
        }

        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            // 3. Đăng nhập vào Firebase
            val user = auth.signInWithCredential(googleCredential).await().user

            if (user == null) {
                GoogleSignInResult.Error("Không lấy được thông tin user từ Firebase")
            } else {
                // 4. MAP thông tin từ FirebaseUser -> AuthUiState của bạn
                val uiState = AuthUiState(
                    emailInput = user.email ?: "",
                    name = user.displayName ?: "", // LẤY TÊN
                    photoUrl = user.photoUrl?.toString() ?: "",
                    birthday = "", // Bỏ trống
                    phoneNumber = "", // Bỏ trống
                    isLoginSuccess = true
                )
                GoogleSignInResult.Success(uiState)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            GoogleSignInResult.Error(e.message ?: "Lỗi đăng nhập Firebase")
        }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    // 4. Lấy người dùng đang đăng nhập (nếu có) và MAP sang AuthUiState
    fun getSignedInUser(): AuthUiState? = auth.currentUser?.let { user ->
        AuthUiState(
            emailInput = user.email ?: "",
            name = user.displayName ?: "", // LẤY TÊN
            photoUrl = user.photoUrl?.toString() ?: "",
            birthday = "", // Bỏ trống
            phoneNumber = "", // Bỏ trống
            isLoginSuccess = true
        )
    }

    // Xây dựng request, lấy Web Client ID từ strings.xml
    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}