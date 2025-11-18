package com.hoangiathinh.smarttasks.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoangiathinh.smarttasks.ui.State.TaskDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val uiState: StateFlow<TaskDetailUiState> = _uiState

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        _uiState.value = TaskDetailUiState.Loading
        viewModelScope.launch {
            try {
                val taskList = repository.getTasks()
                _uiState.value = TaskDetailUiState.Success(taskList)
            } catch (e: Exception) {
                _uiState.value = TaskDetailUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}