package com.example.tobuy.screen.addproduct

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
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
            modifier1 = Modifier.weight(1f),
            modifier2 = Modifier.weight(2f),
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

@Composable
fun LinkParamWithValue(
    text: String,
    modifier1: Modifier,
    modifier2: Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier1,
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

        ClickableLinkInputField(
            modifier = modifier2,
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

    Box(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.matchParentSize()
        )

        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (value.isNotEmpty()) {
            val annotatedString = buildAnnotatedString {
                append(value)
                addStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = 0,
                    end = value.length
                )
            }

            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    launchIntent.value = true
                },
                modifier = Modifier.matchParentSize()
            )
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
