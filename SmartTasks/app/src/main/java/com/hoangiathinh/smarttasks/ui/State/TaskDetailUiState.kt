package com.hoangiathinh.smarttasks.ui.State

import com.hoangiathinh.smarttasks.data.Task

sealed interface TaskDetailUiState {
    object Loading : TaskDetailUiState
    data class Success(val tasks: List<Task>) : TaskDetailUiState
    data class Error(val message: String) : TaskDetailUiState
}