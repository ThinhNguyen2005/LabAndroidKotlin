import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab1.labthuyettrinh.navigationSimple_Colors_3.ColorScreen
import com.example.lab1.labthuyettrinh.navigationSimple_Colors_3.HomeScreen

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable(
            route = "detail/{colorName}/{colorHex}",
            arguments = listOf(
                navArgument("colorName") { type = NavType.StringType },
                navArgument("colorHex") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("colorName") ?: ""
            val hex = backStackEntry.arguments?.getString("colorHex") ?: "FFFFFFFF"

            // Chuyển đổi chuỗi hex thành giá trị màu (Long)
            // Phải thêm "FF" vào đầu để có kênh alpha (độ trong suốt)
            val colorValue = ("FF" + hex).toLong(16)

            // Hiển thị màn hình ColorScreen với dữ liệu đã lấy được
            ColorScreen(navController = navController, colorName = name, colorValue = colorValue)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppNavigationPreview() {
    MyAppNavigation()
}