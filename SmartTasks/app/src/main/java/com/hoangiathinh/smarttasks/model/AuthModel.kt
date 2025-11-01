package com.hoangiathinh.smarttasks.model

import android.app.Application
import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class AuthUiState(
 val emailInput: String = "",
 val name: String = "",
 val birthday: String = "",
 val phoneNumber: String = "",
 val passwordInput: String = "",
 val verificationCodeInput: String = "",
 val isLoading: Boolean = false,
 val errorMessage: String? = null,
 val isLoginSuccess: Boolean = false,
 val photoUrl: String = ""
)


class AuthViewModel(application: Application) : AndroidViewModel(application) {

 private val _uiState = MutableStateFlow(AuthUiState())
 val uiState = _uiState.asStateFlow()
 private val googleAuthClient = AuthGoogle(application.applicationContext)
 private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

 // Hàm này sẽ được gọi từ UI khi có kết quả trả về từ Google
 fun signInWithIntent(intent: Intent) {
  _uiState.update { it.copy(isLoading = true) }

  viewModelScope.launch {
   val result = googleAuthClient.signInWithIntent(intent)
   when (result) {
    is GoogleSignInResult.Success -> {
     _uiState.update {
      // Gán thẳng state đã được map thành công
      result.state.copy(isLoading = false)
     }
    }
    is GoogleSignInResult.Error -> {
     _uiState.update {
      it.copy(isLoading = false, errorMessage = result.message)
     }
    }
   }
  }
 }

 // Kiểm tra xem user đã đăng nhập chưa khi khởi động App
 fun checkForActiveSession() {
  val userState = googleAuthClient.getSignedInUser()
  if (userState != null) {
   _uiState.update { userState }
  }
 }

 // Đăng xuất
 fun signOut() {
  viewModelScope.launch {
   googleAuthClient.signOut()
   _uiState.value = AuthUiState() // Reset về trạng thái ban đầu
  }
 }

 fun clearErrorMessage() {
  _uiState.update { it.copy(errorMessage = null) }
 }

 fun onEmailChange(email: String) {
  _uiState.update { it.copy(emailInput = email) }
 }

 fun onPasswordChange(password: String) {
  _uiState.update { it.copy(passwordInput = password) }
 }

 fun signInWithEmailAndPassword() {
  val current = _uiState.value
  if (current.emailInput.isBlank() || current.passwordInput.isBlank()) {
   _uiState.update { it.copy(errorMessage = "Email và mật khẩu không được để trống") }
   return
  }
  _uiState.update { it.copy(isLoading = true, errorMessage = null) }
  viewModelScope.launch {
   try {
    val result = firebaseAuth.signInWithEmailAndPassword(
     _uiState.value.emailInput,
     _uiState.value.passwordInput
    ).await()
    val user = result.user
    if (user != null) {
     _uiState.update {
      it.copy(
       isLoading = false,
       isLoginSuccess = true,
       emailInput = user.email ?: it.emailInput,
       name = user.displayName ?: it.name,
       photoUrl = user.photoUrl?.toString() ?: it.photoUrl
      )
     }
    } else {
     _uiState.update { it.copy(isLoading = false, errorMessage = "Đăng nhập thất bại") }
    }
   } catch (e: Exception) {
    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Lỗi đăng nhập") }
   }
  }
 }

 fun registerWithEmailAndPassword() {
  val current = _uiState.value
  if (current.emailInput.isBlank() || current.passwordInput.isBlank()) {
   _uiState.update { it.copy(errorMessage = "Email và mật khẩu không được để trống") }
   return
  }
  _uiState.update { it.copy(isLoading = true, errorMessage = null) }
  viewModelScope.launch {
   try {
    val result = firebaseAuth.createUserWithEmailAndPassword(
     _uiState.value.emailInput,
     _uiState.value.passwordInput
    ).await()
    val user = result.user
    if (user != null) {
     _uiState.update {
      it.copy(
       isLoading = false,
       isLoginSuccess = true,
       emailInput = user.email ?: it.emailInput,
       name = user.displayName ?: it.name,
       photoUrl = user.photoUrl?.toString() ?: it.photoUrl
      )
     }
    } else {
     _uiState.update { it.copy(isLoading = false, errorMessage = "Đăng ký thất bại") }
    }
   } catch (e: Exception) {
    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Lỗi đăng ký") }
   }
  }
 }

 suspend fun getGoogleSignInIntentSender(): IntentSender? {
  return googleAuthClient.signIn()
 }
}