package com.mobile.pablo.iosnotes.ui.home

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobile.pablo.iosnotes.ui.destinations.NoteScreenDestination
import com.mobile.pablo.uicomponents.ui.home.HomeBottomBar
import com.mobile.pablo.uicomponents.ui.home.HomeTopBar
import com.mobile.pablo.uicomponents.ui.home.PreviewNoteItem
import com.mobile.pablo.uicomponents.ui.theme.HomeBackground
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val notes by homeViewModel.notes.collectAsState()
    val emptyNoteId = homeViewModel.emptyNoteId

    LaunchedEffect(
        key1 = emptyNoteId
    ) {
        emptyNoteId.collect {
            it?.let {
                navigator.navigate(NoteScreenDestination(noteId = it.toInt()))
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
            constraintSet = homeConstraints
        ) {
            HomeTopBar(
                Modifier
                    .layoutId(ID_HOME_TOP_BAR)
                    .padding(horizontal = Theme.spacing.spacing_14)
                    .fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .layoutId(ID_PREVIEW_NOTE_LISTS)
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
                            onDelete = { homeViewModel.deleteNote(note.id) },
                            onPin = { }
                        )
                    }
                }
            }
            HomeBottomBar(
                5,
                modifier = Modifier
                    .layoutId(ID_HOME_BOTTOM_BAR)
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

private val homeConstraints = ConstraintSet {

    val homeTopBar = createRefFor(ID_HOME_TOP_BAR)
    val previewNoteLists = createRefFor(ID_PREVIEW_NOTE_LISTS)
    val homeBottomBar = createRefFor(ID_HOME_BOTTOM_BAR)

    constrain(homeTopBar) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(previewNoteLists) {
        top.linkTo(homeTopBar.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        height = Dimension.fillToConstraints
        bottom.linkTo(homeBottomBar.top)
    }

    constrain(homeBottomBar) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

// Layout ids
private const val ID_HOME_TOP_BAR = "homeTopBar"
private const val ID_PREVIEW_NOTE_LISTS = "previewNoteLists"
private const val ID_HOME_BOTTOM_BAR = "HomeBottomBar"