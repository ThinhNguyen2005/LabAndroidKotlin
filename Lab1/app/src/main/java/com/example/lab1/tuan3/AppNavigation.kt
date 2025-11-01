package com.example.lab1.tuan3

import LazyColumnLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Import TẤT CẢ các màn hình bạn cần
import com.example.lab1.TextDetail
//import com.example.lab1.ViewImage
//import com.example.lab1.TextField
//import com.example.lab1.PasswordField // Giả sử bạn có màn hình này
//import com.example.lab1.ColumnLayout // Giả sử bạn có màn hình này
//import com.example.lab1.RowLayout // Giả sử bạn có màn hình này


object AppRoutes {
    const val UI_READY = "ui_ready"
    const val UI_LIST = "ui_list"
    const val TEXT_DETAIL = "text_detail"
    const val UI_IMAGE = "ui_image"
    const val UI_INPUT = "ui_input"
    const val UI_INPUTPASSWORD = "ui_inputpassword"
    const val UI_LAYOUTCOLUMN = "ui_layoutcolumn"
    const val UI_LAYOUTROW = "ui_layoutrow"


}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.UI_READY
    ) {
        composable(route = AppRoutes.UI_READY) {
            Tuan3_lab2off(
                onReady = { navController.navigate(AppRoutes.UI_LIST) }
            )
        }
        composable(route = AppRoutes.UI_LIST) {
            ListUI(
                onNavigateToTextDetail = { navController.navigate(AppRoutes.TEXT_DETAIL) },
                onNavigateToImage = { navController.navigate(AppRoutes.UI_IMAGE) },
                onNavigateToTextField = { navController.navigate(AppRoutes.UI_INPUT) },
                onNavigateToPassword = { navController.navigate(AppRoutes.UI_INPUTPASSWORD) },
                onNavigateToColumn = { navController.navigate(AppRoutes.UI_LAYOUTCOLUMN) },
                onNavigateToRow = { navController.navigate(AppRoutes.UI_LAYOUTROW) },
            )
        }


        composable(route = AppRoutes.TEXT_DETAIL) {
            TextDetail(onBackClicked = { navController.popBackStack() })
        }
        composable(route = AppRoutes.UI_IMAGE) {
            ViewImage(onBackClicked = { navController.popBackStack() })
        }
        composable(route = AppRoutes.UI_INPUT) {
            TextField(onBackClicked = { navController.popBackStack() })
        }
        composable(route = AppRoutes.UI_INPUTPASSWORD) {
            EnterPassWord(onBackClicked = { navController.popBackStack() })
        }
        composable(route = AppRoutes.UI_LAYOUTCOLUMN) {
            ColumnLayoutScreen(onBackClicked = { navController.popBackStack() })
        }
        composable(route = AppRoutes.UI_LAYOUTROW) {
            RowLayoutScreen(onBackClicked = { navController.popBackStack() })
        }
        composable(route = AppRoutes.UI_LAYOUTROW) {
            TextDetail(onBackClicked = { navController.popBackStack() })
        }
    }
}