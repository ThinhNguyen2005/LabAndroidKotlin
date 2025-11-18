package com.hoangiathinh.smarttasks.repository

import com.hoangiathinh.smarttasks.api.ApiService
import com.hoangiathinh.smarttasks.api.Task
import retrofit2.Response
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val apiService: ApiService) : TaskRepository  {
    override suspend fun getTasks(): List<Task> {
        // 1. Gọi API, nhận về đối tượng ApiResponse
        val response = apiService.getTasks()
        // 2. Kiểm tra và trả về trường "data" bên trong
        return if (response.isSuccess) {
            response.data
        } else {
            // Nếu API báo lỗi, trả về danh sách rỗng hoặc ném ra exception
            emptyList()
        }
    }
    override suspend fun getTaskById(id: Int): Task {
        // 1. Gọi API, nhận về đối tượng ApiResponse
        val response = apiService.getTaskById(id)
        // 2. Kiểm tra và trả về trường "data" bên trong
        if (response.isSuccess) {
            return response.data
        } else {
            // Nếu không thành công, ném ra lỗi với thông điệp từ API
            throw Exception(response.message)
        }
    }
    override suspend fun deleteTask(id: Int): Response<Unit> {
        return apiService.deleteTask(id)
    }
}
