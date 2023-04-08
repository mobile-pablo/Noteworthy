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
import com.mobile.pablo.domain.data.note.FullNote
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.uicomponents.ui.theme.spacing
import java.util.Date

@Composable
fun TextCanvas(
    modifier: Modifier = Modifier,
    fullNote: FullNote? = null,
    noteId: Int
): FullNote {

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
        fullNote?.let { fullNote ->
            fullNote.fullDescription.map { note ->
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
                    fullNoteId = noteId
                ),
            )
        }
    }

    return fullNote
        ?: FullNote(
            id = noteId,
            title = title.value,
            date = date,
            fullDescription = listOf()
        )
}