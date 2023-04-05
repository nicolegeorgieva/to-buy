package com.example.tobuy.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tobuy.R
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    UI(state = state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun UI(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent()
        },
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetBackgroundColor = Color.White,
        sheetState = bottomSheetState,
        scrimColor = Color.Black.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxSize()
    ) {
        MainContent(bottomState = bottomSheetState)
    }
}

@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text("Your bottom sheet content")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(bottomState: ModalBottomSheetState) {
    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            item(key = "welcome message") {
                Row() {
                    Text(
                        text = stringResource(R.string.welcome_message),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(R.string.welcome_message_app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            item(key = "total price of desired products") {
                Row() {
                    Text(
                        text = stringResource(R.string.total_price_products_section),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "1000 BGN",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }

        val coroutineScope = rememberCoroutineScope()
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
                .size(64.dp),
            shape = CircleShape,
            onClick = {
                coroutineScope.launch {
                    bottomState.show()
                }
            }
        ) {
            Text(
                text = "+",
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
        }
    }
}