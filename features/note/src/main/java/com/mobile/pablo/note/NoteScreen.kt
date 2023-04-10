package com.mobile.pablo.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobile.pablo.iosnotes.ui.destinations.NoteScreenDestination
import com.mobile.pablo.uicomponents.ui.home.NoteBottomBar
import com.mobile.pablo.uicomponents.ui.home.NoteTopBar
import com.mobile.pablo.uicomponents.ui.home.PreviewNoteItem
import com.mobile.pablo.common.theme.HomeBackground
import com.mobile.pablo.common.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun NoteScreen(
    navigator: DestinationsNavigator,
    homeViewModel: NoteViewModel = hiltViewModel()
) {

    val notes = homeViewModel.notes.collectAsState().value
    val emptyNoteId = homeViewModel.emptyNoteId

    LaunchedEffect(
        key1 = emptyNoteId
    ) {
        emptyNoteId.collect {
            it?.let {
                navigator.navigate(EditNoteScreenDestination(noteId = it.toInt()))
            }
        }
    }

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
                    .padding(horizontal = Theme.spacing.spacing_14),
            ) {
                notes?.let { saveNotes ->
                    items(saveNotes) { note ->
                        PreviewNoteItem(
                            note = note!!,
                            onClick = {
                                navigateToNote(
                                    navigator,
                                    note.id
                                )
                            },
                            onDelete = {
                                homeViewModel.deleteNote(note.id)
                                homeViewModel.downloadNotes()
                            },
                            onPin = { }
                        )
                    }
                }
            }
            NoteBottomBar(
                5,
                modifier = Modifier
                    .layoutId(ID_NOTE_BOTTOM_BAR)
                    .fillMaxWidth(),
                onClickNewNote = { homeViewModel.insertEmptyNote() }
            )
        }
    }
}

fun navigateToNote(
    navigator: DestinationsNavigator,
    noteId: Int
) {
    navigator.navigate(NoteScreenDestination(noteId = noteId))
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