package com.mobile.pablo.uicomponents.ui.home

import DateUtils.dayMonthYearFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.uicomponents.ui.theme.font
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.mobile.pablo.uicomponents.ui.util.Theme

@Composable
fun PreviewNoteItem(previewNote: PreviewNote, onClick : () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.spacing.spacing_6))
            .background(Theme.colors.selectedNoteBackground)
            .padding(
                vertical = Theme.spacing.spacing_16,
                horizontal = Theme.spacing.spacing_10
            ).clickable(onClick = onClick)
    ) {
        Column {
            Text(
                text = previewNote.title,
                fontSize = Theme.font.font_15,
                color = Theme.colors.text
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dayMonthYearFormat(previewNote.date),
                    fontSize = Theme.font.font_9,
                    color = Theme.colors.text
                )
                Text(
                    text = previewNote.description,
                    fontSize = Theme.font.font_9,
                    color = Theme.colors.text,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}