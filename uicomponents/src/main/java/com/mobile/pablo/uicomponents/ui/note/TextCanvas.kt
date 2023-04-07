package com.mobile.pablo.uicomponents.ui.note

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.pablo.domain.data.note.FullNote
import com.mobile.pablo.uicomponents.ui.theme.spacing

@Composable
fun TextCanvas(
    modifier: Modifier = Modifier,
    fullNote: FullNote?
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier.padding(top = MaterialTheme.spacing.spacing_12)
    ) {
        item {
            NoteField(modifier = Modifier.fillMaxWidth())
        }
        item {
            NoteField(modifier = Modifier.fillMaxWidth())
        }
    }
}