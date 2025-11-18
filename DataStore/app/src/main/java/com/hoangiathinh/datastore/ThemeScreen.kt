package com.hoangiathinh.datastore


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ThemeScreen(themePreference: ThemePreference) {
    val scope = rememberCoroutineScope()
    val theme by themePreference.themeColor.collectAsState(initial = "Blue")
    var selectedColor by remember { mutableStateOf(theme) }

    // 🔹 Khi theme từ DataStore thay đổi → cập nhật selectedColor
    LaunchedEffect(theme) {
        selectedColor = theme
    }

    val colorMap = mapOf(
        "Blue" to Color(0xFF2196F3),
        "Purple" to Color(0xFF9C27B0),
        "Brown" to Color(0xFF795548)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorMap[selectedColor] ?: Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Setting", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                colorMap.forEach { (name, color) ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(color)
                            .clickable { selectedColor = name }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                scope.launch {
                    themePreference.saveTheme(selectedColor)
                }
            }) {
                Text("Apply")
            }
        }
    }
}
