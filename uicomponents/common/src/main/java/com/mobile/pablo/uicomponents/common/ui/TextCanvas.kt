package com.mobile.pablo.uicomponents.common.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.uicomponents.common.R
import com.mobile.pablo.uicomponents.common.theme.spacing
import java.util.Date
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun TextCanvas(
    modifier: Modifier = Modifier,
    note: Note,
    noteId: Int,
    createEmptyNoteLine: () -> Long = { 0L }
): Note {

    val listState = rememberLazyListState()
    val title = remember { mutableStateOf(EMPTY_STRING) }

    val defaultDescription = if (note.description?.isNotEmpty() == true) note.description else null
    val noteLines = remember { mutableStateOf(defaultDescription) }.value

    val localNoteLines = mutableListOf<NoteLine>()
    LazyColumn(
        state = listState,
        modifier = modifier.padding(top = Theme.spacing.spacing_12)
    ) {
        item {
            title.value = NoteField(
                modifier = Modifier.fillMaxWidth(),
                title = note.title,
                placeHolder = stringResource(id = R.string.title)
            )
        }

        if (noteLines != null) {
                items(noteLines) { noteLine ->
                    val localNoteLine = NoteField(
                        modifier = Modifier.fillMaxWidth(),
                        noteLine = noteLine.copy(parentNoteId = noteId),
                        hasCheckbox = true
                    )

                    localNoteLines.add(localNoteLine)
                }
        } else {
            item {
                val localNoteLine = NoteField(
                    modifier = Modifier.fillMaxWidth(),
                    noteLine = NoteLine(parentNoteId = noteId),
                    createEmptyNoteLine = createEmptyNoteLine
                )

                localNoteLines.add(localNoteLine)
            }
        }
    }

    return note.copy(
        title = title.value,
        date = Date(),
        description = localNoteLines
    )
}
