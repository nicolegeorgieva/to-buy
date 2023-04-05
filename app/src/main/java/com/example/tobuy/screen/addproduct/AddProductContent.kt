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

        ParamWithValue(text = "Name")

        ParamWithValue(text = "Link")

        ParamWithValue(text = "Image")

        ParamWithValue(text = "Description")

        ParamWithValue(text = "Category")

        ParamWithValue(text = "Price")

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

@Composable
fun ParamWithValue(text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier.weight(2f),
            text = "_",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    Spacer(modifier = Modifier.height(12.dp))
}