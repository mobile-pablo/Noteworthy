package com.mobile.pablo.uicomponents.common.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.uicomponents.common.R
import com.mobile.pablo.uicomponents.common.theme.spacing
import com.mobile.pablo.uicomponents.common.util.testTag
import java.util.Date
import androidx.compose.material.MaterialTheme as Theme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextCanvas(
    modifier: Modifier = Modifier,
    note: Note,
    noteId: Int,
    createEmptyNoteLine: () -> Long = { 0L }
): Note {

    val listState = rememberLazyListState()
    val title = remember { mutableStateOf(EMPTY_STRING) }

    val description = note.description

    val localNoteLines = mutableListOf<NoteLine>()
    LazyColumn(
        state = listState,
        modifier = modifier.padding(top = Theme.spacing.spacing_12)
            .semantics {
                testTagsAsResourceId = true
            }
    ) {
        item {
            title.value = NoteField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(R.string.title_id),
                title = note.title,
                placeHolder = stringResource(id = R.string.title)
            )
        }

        if (description.isNotEmpty()) {
            items(description) { noteLine ->
                val localNoteLine = NoteField(
                    modifier = Modifier.fillMaxWidth().testTag("noteLine-${noteLine.id}"),
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
                    createEmptyNoteLine = createEmptyNoteLine,
                    hasCheckbox = true
                )

                localNoteLines.add(localNoteLine)
            }
        }
    }

    return Note(
        id = note.id,
        title = title.value,
        date = Date(),
        description = localNoteLines
    )
}
