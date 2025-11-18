package com.hoangiathinh.smarttasks.ui.screen//package com.hoangiathinh.smarttasks.ui.screen
//
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Task
//import androidx.compose.material3.*
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.*
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.hoangiathinh.smarttasks.data.*
//import com.hoangiathinh.smarttasks.data.TaskListViewModel
//@OptIn(ExperimentalMaterial3Api::class)
//
//@Composable
//fun HomeScreen(
//    onTaskClick: (Int) -> Unit,
//    viewModel: TaskListViewModel = hiltViewModel()
//) {
//
//}
//@Composable
//fun TaskCard(task: Task, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = "Status: ${task.status}", style = MaterialTheme.typography.bodySmall)
//            // Thêm các thông tin khác nếu cần
//        }
//    }
//}
//
//@Composable
//fun EmptyTasksView() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Icon(
//                imageVector = Icons.Default.Task, // Thay bằng icon của bạn
//                contentDescription = null,
//                modifier = Modifier.size(120.dp)
//            )
//            Text(text = "No Tasks Yet", style = MaterialTheme.typography.headlineSmall)
//            Text(text = "Stay productive - add something to do")
//        }
//    }
//}
//
