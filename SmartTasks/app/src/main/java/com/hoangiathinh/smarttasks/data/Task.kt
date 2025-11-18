package com.hoangiathinh.smarttasks.data

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val dueDate: String,
    val subtasks: List<Subtask>,
    val attachments: List<Attachment>
)

data class Subtask(
    val id: String,
    val title: String,
    val isCompleted: Boolean
)

data class Attachment(
    val name: String,
    val url: String
)
