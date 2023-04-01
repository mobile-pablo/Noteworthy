package com.mobile.pablo.iosnotes.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.mobile.pablo.uicomponents.ui.util.Theme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun NoteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Theme.spacing.spacing_6)
    ) {
    }
}
// Dodac long press akcje

@Composable
fun TopNavButtons() {
}