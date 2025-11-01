package com.example.lab1.tuan3


import android.graphics.Color.rgb
import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun ListUI(
    onNavigateToTextDetail: () -> Unit = {},
    onNavigateToImage: () -> Unit = {},
    onNavigateToTextField: () -> Unit = {},
    onNavigateToPassword: () -> Unit = {},
    onNavigateToColumn: () -> Unit = {},
    onNavigateToRow: () -> Unit = {}



){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()), // ✅ Thêm dòng này để cho phép cuộn
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "UI Components List",
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(rgb(70, 142, 200))
        )
        Text(
            text = "Display",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        // Card hiển thị item
        Card(
            onClick = onNavigateToTextDetail,
            modifier = Modifier
                .fillMaxWidth()
                .padding( bottom = 20.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(rgb(187, 222, 252))
            )
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "Text",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Display text",
                    fontSize = 20.sp,
                    )
            }
        }
        Card(
            onClick = onNavigateToImage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(rgb(187, 222, 252))
            )
        ) {
            Column (
                modifier = Modifier.padding(18.dp),

            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 5.dp),
                    text = "Image",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Display an image",
                    fontSize = 20.sp
                )
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = "Input",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp

        )
        Card(
            onClick = onNavigateToTextField,
            modifier = Modifier
                .fillMaxWidth()
                .padding( bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(rgb(187, 222, 252))

            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ){
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "TextField",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Input field for text",
                    fontSize = 20.sp
                )

            }
        }
        Card(
            onClick = onNavigateToPassword,
            modifier = Modifier
                .fillMaxWidth()
                .padding( bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(rgb(187, 222, 252))
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)

            ) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "PasswordField",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Input field for password",
                    fontSize = 20.sp
                )

            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = "Layout",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp

        )
        Card(
            onClick = onNavigateToColumn,
            modifier = Modifier
                .fillMaxWidth()
                .padding( bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(rgb(187, 222, 252))

            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "Column",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Araynges alements vertically",
                    fontSize = 20.sp


                )
            }
        }
        Card(
            onClick = onNavigateToRow,
            modifier = Modifier
                .fillMaxWidth()
                .padding( bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(rgb(187, 222, 252))
            )
        ) {
                Column(modifier = Modifier.padding(20.dp)){
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "Row",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Araynges alements horizontally",
                        fontSize = 20.sp
                    )
                }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListUIPreview() {
    ListUI()
}