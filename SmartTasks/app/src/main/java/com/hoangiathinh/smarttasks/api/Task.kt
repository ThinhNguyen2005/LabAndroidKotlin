package com.hoangiathinh.smarttasks.api

data class Task(
    val id: Int,
    val title: String?,
    val description: String?,
    val category: String?,
    val status: String?,
    val priority: String?,
    val subtasks: List<Subtask>?,
    val attachments: List<Attachment>?
)
data class Subtask(
    val title: String?,
    val isCompleted: Boolean
)
data class Attachment(
    val name: String?,
    val url: String?
)