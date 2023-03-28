package com.mobile.pablo.uicomponents.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreviewNoteItem() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 20.dp,
                horizontal = 10.dp
            )
    ) {
        Column {
            Text(
                text = "Monday | 27.03",
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}