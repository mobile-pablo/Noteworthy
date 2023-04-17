package com.mobile.pablo.note.mock

import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.uicomponents.common.theme.HomeBackground
import com.mobile.pablo.uicomponents.common.theme.spacing
import com.mobile.pablo.uicomponents.note.NoteBottomBar
import com.mobile.pablo.uicomponents.note.NoteTopBar
import com.mobile.pablo.uicomponents.note.PreviewNoteItem
import java.util.Date

@Composable
fun MockNoteScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.HomeBackground)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = noteConstraints
        ) {
            NoteTopBar(
                Modifier
                    .layoutId(ID_NOTE_TOP_BAR)
                    .padding(horizontal = Theme.spacing.spacing_14)
                    .fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .layoutId(ID_NOTE_LISTS)
                    .padding(horizontal = Theme.spacing.spacing_14)
            ) {
                items(MOCK_NOTE_LIST) { note ->
                    PreviewNoteItem(
                        modifier = Modifier.testTag("note-${note.id}"),
                        note = note,
                        onClick = {},
                        onDelete = {
                            MOCK_NOTE_LIST.removeAt(note.id)
                        },
                        onPin = { }
                    )
                }
            }
            NoteBottomBar(
                MOCK_NOTE_LIST.size,
                modifier = Modifier
                    .layoutId(ID_NOTE_BOTTOM_BAR)
                    .fillMaxWidth(),
                onClickNewNote = {
                    var lastNoteId = MOCK_NOTE_LIST.last().id
                    lastNoteId++
                    MOCK_NOTE_LIST.add(
                        Note(
                            id = lastNoteId,
                            title = "Another title",
                            date = Date(),
                            description = listOf(
                                NoteLine(
                                    0,
                                    lastNoteId,
                                    true,
                                    "This is a checkbox"
                                )
                            )
                        )
                    )
                }
            )
        }
    }
}

private val noteConstraints = ConstraintSet {

    val noteTopBar = createRefFor(ID_NOTE_TOP_BAR)
    val noteLists = createRefFor(ID_NOTE_LISTS)
    val noteBottomBar = createRefFor(ID_NOTE_BOTTOM_BAR)

    constrain(noteTopBar) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(noteLists) {
        top.linkTo(noteTopBar.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        height = Dimension.fillToConstraints
        bottom.linkTo(noteBottomBar.top)
    }

    constrain(noteBottomBar) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

// Layout ids
private const val ID_NOTE_TOP_BAR = "noteTopBar"
private const val ID_NOTE_LISTS = "previewNoteLists"
private const val ID_NOTE_BOTTOM_BAR = "noteBottomBar"

val MOCK_NOTE_LIST = mutableListOf(
    Note(
        id = 0,
        title = "Hello",
        date = Date(),
        description = listOf(
            NoteLine(
                0,
                0,
                true,
                "This is a checkbox"
            )
        )
    ),
    Note(
        id = 1,
        title = "Hello",
        date = Date(),
        description = listOf(
            NoteLine(
                2,
                1,
                true,
                "This is a checkbox"
            )
        )
    )
)
