package com.mobile.pablo.uicomponents.ui.home

import DateUtils.dayMonthYearFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.pablo.domain.data.home.PreviewNote

@Composable
fun PreviewNoteItem(previewNote: PreviewNote) {
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
                text = previewNote.title,
                fontSize = 15.sp,
                color = Color.White
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = dayMonthYearFormat(previewNote.date),
                    fontSize = 10.sp,
                    color = Color.White
                )
                Text(
                    text = previewNote.description,
                    fontSize = 10.sp,
                    color = Color.White,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}