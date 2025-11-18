package com.hoangiathinh.smarttasks.ui.screen.Detail
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.hoangiathinh.smarttasks.data.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    onNavigateUp: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    // Sử dụng LaunchedEffect để gọi API một lần khi màn hình được hiển thị
    LaunchedEffect(key1 = taskId) {
        viewModel.fetchTaskById(taskId)
    }

    val taskState by viewModel.task.collectAsState() // Giả sử ViewModel có StateFlow 'task'

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        taskState?.let { task ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(text = task.title, style = MaterialTheme.typography.headlineMedium)
                    Text(text = task.description)
                    // Hiển thị Category, Status, Priority
                }

                item {
                    Text("Subtasks", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
                }
                items(task.subtasks) { subtask ->
                    SubtaskItem(subtask)
                }

                // Tương tự cho Attachments
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator() // Hiển thị trong lúc tải
        }
    }
}

@Composable
fun SubtaskItem(subtask: Subtask) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = subtask.isCompleted, onCheckedChange = null)
        Text(text = subtask.title)
    }
}