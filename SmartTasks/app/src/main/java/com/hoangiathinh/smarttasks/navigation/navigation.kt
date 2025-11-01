package com.hoangiathinh.smarttasks.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hoangiathinh.smarttasks.model.AuthViewModel
import com.hoangiathinh.smarttasks.ui.screen.*

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object InputEmail : Screen("input_email_screen")
    object VerifyCode : Screen("verify_code_screen")
    object CreatePassword : Screen("create_password_screen")
    object Profile : Screen("profile_screen")
    object MainApp : Screen("main_app_screen")
    object Home : Screen("home_screen")

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
        composable(Screen.ProductDetail.route) {
            ProductDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }



//        composable(Screen.VerifyCode.route) {
//            VerificationScreen(
//                authViewModel = authViewModel,
//                onNext = { /* ... */ },
//                onBack = { navController.popBackStack() }
//            )
//        }

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

        composable(Screen.MainApp.route) {

        }
    }
}