package com.example.tobuy.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(onBack: () -> Unit) {
    OutlinedButton(
        modifier = Modifier.size(48.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = { onBack() },
        border = BorderStroke(width = 1.dp, color = Color(0xFFBBB9B9)),
        shape = CircleShape
    ) {
        Text(text = "X")
    }

    BackHandler {
        onBack()
    }
}