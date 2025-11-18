package com.hoangiathinh.smarttasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoangiathinh.smarttasks.api.Task
import com.hoangiathinh.smarttasks.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?> = _task

    fun fetchTaskById(id: Int) {
        viewModelScope.launch {
            try {
                _task.value = repository.getTaskById(id)
            } catch (e: Exception) {
                Log.e("TaskDetailViewModel", "Loi loi khi lay task", e)


            }
        }
    }
}