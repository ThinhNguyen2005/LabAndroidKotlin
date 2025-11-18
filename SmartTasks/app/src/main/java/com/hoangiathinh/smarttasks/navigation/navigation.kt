package com.hoangiathinh.smarttasks.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hoangiathinh.smarttasks.model.AuthViewModel
import com.hoangiathinh.smarttasks.ui.screen.Login.CreatePasswordScreen
import com.hoangiathinh.smarttasks.ui.screen.Login.InputEmailScreen
import com.hoangiathinh.smarttasks.ui.screen.Login.LoginScreen
import com.hoangiathinh.smarttasks.ui.screen.Login.ProfileScreen
import com.hoangiathinh.smarttasks.ui.screen.TaskDetailScreen
import com.hoangiathinh.smarttasks.ui.screen.TaskListScreen

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object InputEmail : Screen("input_email_screen")
    object VerifyCode : Screen("verify_code_screen")
    object CreatePassword : Screen("create_password_screen")
    object Profile : Screen("profile_screen")
    object MainApp : Screen("main_app_screen")
    object Home : Screen("home_screen")
    object TaskList : Screen("task_list_screen")
    object TaskDetail : Screen("task_detail_screen")
    object ProductDetail : Screen("product_detail_screen")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToProfile = {
                    // Xóa tất cả các màn hình trước đó và đi đến Profile
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.InputEmail.route)
                }

            )
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(
                authViewModel = authViewModel,
                onSignOut = {
                    authViewModel.signOut()
                    // Quay về Login và xóa Profile khỏi back stack
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Profile.route) { inclusive = true }
                    }
                },
                onProductDetail = {
                    navController.navigate(Screen.ProductDetail.route)
                }
            )
        }

        composable(Screen.InputEmail.route) {
            InputEmailScreen(
                authViewModel = authViewModel,
                onNext = {
                    navController.navigate(Screen.CreatePassword.route)
                },
                onBack = { navController.popBackStack() },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.CreatePassword.route) {
            CreatePasswordScreen(
                authViewModel = authViewModel,
                onNext = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.TaskList.route){
            TaskListScreen(
                onTaskClick = { taskId ->
                    // Log ra ID ngay trước khi điều hướng để chắc chắn nó đúng
                    Log.d("AppNavHost", "Navigating to detail screen with ID: $taskId")
                    navController.navigate("${Screen.TaskDetail.route}/$taskId")
                }
            )
        }
        composable(
            Screen.TaskDetail.routeWithArgs,
            arguments = Screen.TaskDetail.arguments
        ) { navBackStackEntry ->
            // SỬA LẠI ĐỂ AN TOÀN HƠN
            try {
                // Lấy argument một cách an toàn
                val taskId = navBackStackEntry.arguments?.getInt(Screen.TaskDetail.taskIdArg)

                // Kiểm tra taskId một cách chặt chẽ
                if (taskId != null && taskId != 0) { // Kiểm tra cả trường hợp khác 0 nếu cần
                    Log.d("AppNavHost", "Successfully received ID in detail screen: $taskId")
                    TaskDetailScreen(
                        taskId = taskId,
                        onNavigateUp = { navController.popBackStack() }
                    )
                } else {
                    // Nếu taskId không hợp lệ, ghi log và có thể hiển thị một màn hình lỗi
                    Log.e("AppNavHost", "Error: Received invalid or null taskId.")
                    // TODO: Hiển thị một Composable báo lỗi thay vì để màn hình trắng
                    // Hoặc tự động quay lại: navController.popBackStack()
                }
            } catch (e: Exception) {
                // Bắt bất kỳ lỗi nào khác có thể xảy ra khi lấy argument
                Log.e("AppNavHost", "Crash while retrieving taskId", e)
                // TODO: Xử lý lỗi, ví dụ quay lại màn hình trước
                // navController.popBackStack()
            }
        }
        composable(Screen.MainApp.route) {

        }
    }
}

@Composable
fun BottomBarSmartTask(){

}