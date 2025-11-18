package com.hoangiathinh.smarttasks.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hoangiathinh.smarttasks.TaskDetailViewModel
import com.hoangiathinh.smarttasks.api.Attachment
import com.hoangiathinh.smarttasks.api.Subtask


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    onNavigateUp: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = taskId) {
        viewModel.fetchTaskById(taskId)
    }

    val task by viewModel.task.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Xử lý xóa */ }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                    }
                }
            )
        }
    ) { paddingValues ->
        task?.let { taskDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentPadding = PaddingValues(16.dp)
            ) {
                // Tiêu đề và mô tả
                item {
                    Column {
                        Text(
                            text = taskDetail.title?: "",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = taskDetail.description?:"",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                // Thông tin chi tiết
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InfoChip("Category", taskDetail.category?:"")
                        InfoChip("Status", taskDetail.status?:"")
                        InfoChip("Priority", taskDetail.priority?:"")
                    }
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }

                // Subtasks
                item {
                    Text("Subtasks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
                items(taskDetail.subtasks?: emptyList()) { subtask ->
                    SubtaskItem(subtask)
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }

                // Attachments
                item {
                    Text("Attachments", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
                items(taskDetail.attachments?: emptyList()) { attachment ->
                    AttachmentItem(attachment)
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun InfoChip(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun SubtaskItem(subtask: Subtask) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Checkbox(checked = subtask.isCompleted, onCheckedChange = null)
        Text(text = subtask.title?:"", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun AttachmentItem(attachment: Attachment) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Icon(Icons.Default.AttachFile, contentDescription = "Attachment Icon", tint = Color.Gray)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = attachment.name?:"", style = MaterialTheme.typography.bodyMedium)
    }
}