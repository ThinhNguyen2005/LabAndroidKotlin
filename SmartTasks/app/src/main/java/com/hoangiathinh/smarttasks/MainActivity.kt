//package com.hoangiathinh.smarttasks
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.rememberNavController
//import com.google.firebase.auth.FirebaseAuth
//import com.hoangiathinh.smarttasks.navigation.AppNavHost
//import com.hoangiathinh.smarttasks.navigation.Screen
//import com.hoangiathinh.smarttasks.ui.theme.SmartTasksTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            SmartTasksTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
//
//                    // Xác định màn hình bắt đầu dựa trên trạng thái đăng nhập
//                    val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
//                        // Nếu đã đăng nhập, đi thẳng vào màn hình chính
//                        Screen.MainApp.route
//                    } else {
//                        // Nếu chưa, bắt đầu từ màn hình đăng nhập
//                        Screen.Login.route
//                    }
//
//                    AppNavHost(
//                        navController = navController,
//                        startDestination = startDestination
//                    )
//                }
//            }
//        }
//    }
//}

package com.hoangiathinh.smarttasks

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.hoangiathinh.smarttasks.navigation.AppNavHost
import com.hoangiathinh.smarttasks.navigation.Screen
import com.hoangiathinh.smarttasks.ui.theme.SmartTasksTheme

class MainActivity : ComponentActivity() {

    // --- BẮT ĐẦU PHẦN TÍCH HỢP FCM ---

    companion object {
        private const val TAG = "MainActivityFCM"
    }

    // 1. Trình khởi chạy để yêu cầu quyền
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Quyền gửi thông báo đã được cấp.", Toast.LENGTH_SHORT).show()
        } else {
            // Có thể thông báo cho người dùng về tầm quan trọng của việc bật thông báo
            Toast.makeText(this, "Bạn đã từ chối quyền gửi thông báo.", Toast.LENGTH_LONG).show()
        }
    }

    // 2. Hàm để kiểm tra và yêu cầu quyền
    private fun askNotificationPermission() {
        // Chỉ áp dụng cho Android 13 (API 33) trở lên
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Kiểm tra xem quyền đã được cấp chưa
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // Quyền đã được cấp, không cần làm gì thêm
                Log.d(TAG, "Quyền gửi thông báo đã được cấp từ trước.")
            } else {
                // Yêu cầu quyền
                Log.d(TAG, "Đang yêu cầu quyền gửi thông báo...")
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    // --- KẾT THÚC PHẦN TÍCH HỢP FCM ---


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3. Gọi hàm yêu cầu quyền và lấy token ngay khi Activity khởi chạy
        askNotificationPermission()
        retrieveAndLogFcmToken()

        enableEdgeToEdge()
        setContent {
            SmartTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Xác định màn hình bắt đầu dựa trên trạng thái đăng nhập
                    val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
                        // Nếu đã đăng nhập, đi thẳng vào màn hình chính
                        Screen.MainApp.route
                    } else {
                        // Nếu chưa, bắt đầu từ màn hình đăng nhập
                        Screen.Login.route
                    }

                    AppNavHost(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }

    // 4. Tách logic lấy token ra một hàm riêng cho gọn gàng
    private fun retrieveAndLogFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Lấy FCM registration token thất bại", task.exception)
                return@OnCompleteListener
            }

            // Lấy token mới
            val token = task.result

            // Log token ra để debug
            val msg = "FCM Registration Token: $token"
            Log.d(TAG, msg)

            // Bạn có thể bỏ Toast này đi nếu không muốn làm phiền người dùng
            // Toast.makeText(baseContext, "Token đã được lấy (xem trong Logcat)", Toast.LENGTH_SHORT).show()
        })
    }
}