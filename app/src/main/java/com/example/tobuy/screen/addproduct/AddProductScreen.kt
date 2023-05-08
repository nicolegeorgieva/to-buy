package com.example.tobuy.screen.addproduct

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tobuy.R
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
        if (value.isNotEmpty()) {
            InputField(
                modifier = Modifier.weight(1f),
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = placeholder
            )

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                shape = CircleShape,
                onClick = {
                    launchIntent.value = true
                }
            ) {
                Text("Open")
            }

            Spacer(modifier = Modifier.width(2.dp))

            val context = LocalContext.current

            IconButton(
                onClick = {
                    val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("link", value)
                    clipboard.setPrimaryClip(clip)
                }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_content_copy_24),
                    contentDescription = "Copy link"
                )
            }
        } else {
            InputField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = placeholder
            )
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
}
