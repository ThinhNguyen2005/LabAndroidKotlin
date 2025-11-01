import android.graphics.Color.rgb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn // ✨ Quan trọng: Import LazyColumn
import androidx.compose.foundation.lazy.items // ✨ Import này dùng cho LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lab1.tuan3.AppRoutes

data class CardInfo(val id: Int, val title: String, val description: String)

@Composable
fun LazyColumnLayout(
    cardItems: List<CardInfo>,
    onItemClick: (Int) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(items = cardItems, key = { it.id }) { itemData ->
            Card(
                onClick = { onItemClick(itemData.id) },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(rgb(187, 222, 252))
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "${itemData.title} ${itemData.id}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = itemData.description,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetail(text: String, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Màn hình chi tiết") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Quay lại")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text, fontSize = 24.sp, modifier = Modifier.padding(8.dp))
                androidx.compose.material3.Button(
                    onClick = { navController.navigate(AppRoutes.UI_READY) },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Quay về màn hình Ready")
                }
            }
        }
    }
}
