package com.mobile.pablo.note

import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.pablo.addnote.destinations.AddNoteScreenDestination
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.editnote.destinations.EditNoteScreenDestination
import com.mobile.pablo.uicomponents.common.theme.HomeBackground
import com.mobile.pablo.uicomponents.common.theme.spacing
import com.mobile.pablo.uicomponents.note.NoteBottomBar
import com.mobile.pablo.uicomponents.note.NoteTopBar
import com.mobile.pablo.uicomponents.note.PreviewNoteItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.utils.navGraph

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun NoteScreen(
    navController: NavController = rememberNavController(),
    noteViewModel: NoteViewModel = hiltViewModel()
) {

    val notes = noteViewModel.notes.collectAsStateWithLifecycle(listOf()).value
    val viewState = noteViewModel.viewState.collectAsStateWithLifecycle().value
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    LaunchedEffect(
        key1 = viewState,
        key2 = notes
    ) {
        when (viewState) {
            is ViewState.InsertSuccessful -> {
                viewState.noteId?.let { noteID ->
                    navigateToAddNote(
                        navController,
                        noteID.toInt()
                    )
                    noteViewModel.setEmptyNote(null)
                }
            }

            is ViewState.Message -> {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = viewState.message
                )
            }

            is ViewState.Default -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.HomeBackground)
            .semantics {
                testTagsAsResourceId = true
            }
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
                if (notes.isNotEmpty()) {
                    items(notes) { saveNotes ->
                        saveNotes?.let {
                            PreviewNoteItem(note = it,
                                onClick = {
                                    navigateToEditNote(
                                        navController,
                                        it
                                    )
                                },
                                onDelete = {
                                    noteViewModel.deleteNote(it.id)
                                },
                                onPin = { })
                        }
                    }
                }
            }
            NoteBottomBar(notes.size,
                modifier = Modifier
                    .layoutId(ID_NOTE_BOTTOM_BAR)
                    .fillMaxWidth(),
                onClickNewNote = { noteViewModel.insertEmptyNote() })
        }
    }
}

fun navigateToEditNote(
    navController: NavController,
    note: Note
) {
    val editNoteDestination = EditNoteScreenDestination(note = note)
    navController.navigate(editNoteDestination within navController.currentBackStackEntry!!.navGraph())
}

fun navigateToAddNote(
    navController: NavController,
    noteId: Int
) {
    val addNoteDestination = AddNoteScreenDestination(noteId = noteId)
    navController.navigate(addNoteDestination within navController.currentBackStackEntry!!.navGraph())
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
