package com.hoangiathinh.smarttasks.repository

import com.hoangiathinh.smarttasks.api.Task
import retrofit2.Response


interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun getTaskById(id: Int): Task

    suspend fun deleteTask(id: Int): Response<Unit>

}