package com.mobile.pablo.iosnotes.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.uicomponents.ui.home.PreviewNoteItem
import java.util.*

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        LazyColumn(content = {
            item {
                PreviewNoteItem(
                    previewNote = PreviewNote(
                        0,
                        "Wtorek | 30.03",
                        Date(42L),
                        "Lorem ipsum dolet sit amet"
                    )
                )
            }
        })
    }
}