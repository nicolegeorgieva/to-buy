package com.example.tobuy.screen.addproduct

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text("Add new product", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Name", style = MaterialTheme.typography.bodyLarge)
        Text("Link", style = MaterialTheme.typography.bodyLarge)
        Text("Image", style = MaterialTheme.typography.bodyLarge)
        Text("Description", style = MaterialTheme.typography.bodyLarge)
        Text("Category", style = MaterialTheme.typography.bodyLarge)
        Text("Price", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Save"
            )
        }
    }
}