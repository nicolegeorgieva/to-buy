package com.example.tobuy.screen.addproduct

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tobuy.component.BackButton
import com.example.tobuy.component.InputField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProductScreen(bottomSheetState: ModalBottomSheetState) {
    val viewModel: AddProductViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    BottomSheetContent(
        state = state,
        onEvent = viewModel::onEvent,
        bottomSheetState = bottomSheetState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(
    state: AddProductState,
    onEvent: (AddProductEvent) -> Unit,
    bottomSheetState: ModalBottomSheetState
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row() {
            Text("Add new product", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.weight(1f))

            BackButton(
                onBack = {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ParamWithValue(text = "Name", value = "", placeholder = "Enter name")

        LinkParamWithValue(
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
    Column {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange
        )
    }

    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun LinkParamWithValue(
    text: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit = {}
) {
    Column {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        ClickableLinkInputField(
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange
        )
    }

    Spacer(modifier = Modifier.height(12.dp))
}

@SuppressLint("RememberReturnType")
@Composable
fun ClickableLinkInputField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val launchIntent = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputField(
            modifier = Modifier.weight(2f),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = placeholder
        )

        if (value.isNotEmpty()) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    launchIntent.value = true
                }
            ) {
                Text("Open")
            }
        }
    }

    DisposableEffect(launchIntent.value) {
        if (launchIntent.value) {
            coroutineScope.launch {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(value))
                context.startActivity(intent)
                launchIntent.value = false
            }
        }

        onDispose { }
    }
}
