package com.mobile.pablo.uicomponents.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobile.pablo.uicomponents.ui.theme.font
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.mobile.pablo.uicomponents.ui.util.Theme
import kotlinx.coroutines.launch

@Composable
fun BottomHomeBar(
    amountNotes: Int,
    modifier: Modifier,
    onClickNewNote: () -> Unit
) {

    val buttonScope = rememberCoroutineScope()

    var notesText by remember { mutableStateOf("") }

    LaunchedEffect(amountNotes) {
        notesText = "$amountNotes notes"
    }
    Box(
        modifier
            .fillMaxWidth()
            .background(Theme.colors.topBarSelectedItemBackground)
            .padding(vertical = Theme.spacing.spacing_14)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = notesText,
                fontSize = Theme.font.font_15,
                color = Theme.colors.text,
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(
                onClick = {
                    buttonScope.launch { onClickNewNote() }
                }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "New File",
                    tint = Theme.colors.selectedNoteBackground,
                    modifier = Modifier.size(Theme.spacing.spacing_24)
                )
            }
        }
    }
}