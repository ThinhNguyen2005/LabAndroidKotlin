package com.hoangiathinh.smarttasks.model


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoangiathinh.smarttasks.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

// Lớp trạng thái cho UI
data class ProductUiState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ProductViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) } // Reset lỗi cũ
            try {
                val productData = RetrofitInstance.api.getProduct()
                _uiState.update {
                    it.copy(product = productData, isLoading = false)
                }
            } catch (e: IOException) {
                // Lỗi kết nối mạng
                Log.e("ProductViewModel", "Network error", e)
                _uiState.update {
                    it.copy(errorMessage = "Lỗi mạng. Vui lòng kiểm tra kết nối.", isLoading = false)
                }
            } catch (e: Exception) {
                // SỬA LỖI: In ra lỗi thực sự vào Logcat
                Log.e("ProductViewModel", "An unexpected error occurred", e)
                _uiState.update {
                    it.copy(errorMessage = "Đã xảy ra lỗi không mong muốn.", isLoading = false)
                }
            }
        }
    }
}