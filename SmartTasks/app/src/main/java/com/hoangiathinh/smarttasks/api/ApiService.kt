package com.hoangiathinh.smarttasks.api

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface  ApiService {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): ApiResponse<List<Task>>

    @GET("researchUTH/task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): ApiResponse<Task>

    @DELETE("researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}