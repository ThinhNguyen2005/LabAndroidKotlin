package com.hoangiathinh.smarttasks.data


import com.hoangiathinh.smarttasks.model.Product
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiService {
    @GET("v2/product")
    suspend fun getProduct(): Product
}

// Tạo một đối tượng Retrofit duy nhất
object RetrofitInstance {
    private val json = Json { ignoreUnknownKeys = true }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://mock.apidog.com/m1/890655-872447-default/v2/product")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)
    }
}