package com.mobile.pablo.uicomponents.ui.note

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.uicomponents.ui.theme.spacing
import java.util.Date

@Composable
fun TextCanvas(
    modifier: Modifier = Modifier,
    note: Note? = null,
    noteId: Int
): Note {

    val listState = rememberLazyListState()
    var title = remember { mutableStateOf("") }
    val date = Date()

    LazyColumn(
        state = listState,
        modifier = modifier.padding(top = MaterialTheme.spacing.spacing_12)
    ) {
        item {
            title.value = NoteField(
                modifier = Modifier.fillMaxWidth(),
                title = ""
            )
        }
        note?.let { currentNote ->
            currentNote.description.map { note ->
                item {
                    note?.let {
                        NoteField(
                            modifier = Modifier.fillMaxWidth(),
                            noteLine = note
                        )
                    }
                }
            }
        } ?: item {
            NoteField(
                modifier = Modifier.fillMaxWidth(),
                noteLine = NoteLine(
                    id = 0,
                    isCheckbox = false,
                    noteText = "",
                    parentNoteId = noteId
                ),
            )
        }
    }

    return note
        ?: Note(
            id = noteId,
            title = title.value,
            date = date,
            description = listOf()
        )
}