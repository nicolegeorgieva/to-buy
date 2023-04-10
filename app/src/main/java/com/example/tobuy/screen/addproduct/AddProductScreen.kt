package com.example.tobuy.screen.addproduct

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tobuy.component.InputField

@Composable
fun AddProductScreen() {
    val viewModel: AddProductViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    BottomSheetContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
fun BottomSheetContent(
    state: AddProductState,
    onEvent: (AddProductEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text("Add new product", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        ParamWithValue(text = "Name", value = "", placeholder = "Enter name")

        ParamWithValue(
            text = "Link",
            value = state.sharedText.ifEmpty { "" },
            placeholder = "Enter link"
        )

        ParamWithValue(text = "Image", value = "", placeholder = "Enter image link")

        ParamWithValue(text = "Description", value = "", placeholder = "Enter description")

        ParamWithValue(text = "Category", value = "", placeholder = "Enter category")

        ParamWithValue(text = "Price", value = "", placeholder = "Enter price")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                //TODO
            }
        ) {
            Text(
                text = "Save"
            )
        }
    }
}

@Composable
fun ParamWithValue(
    text: String, value: String, placeholder: String, onValueChange: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

        InputField(
            modifier = Modifier.weight(2f),
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange
        )
    }

    Spacer(modifier = Modifier.height(12.dp))
}