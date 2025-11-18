package com.hoangiathinh.smarttasks.api

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T
)