package com.mobile.pablo.iosnotes.ui.note

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobile.pablo.uicomponents.data.NoteBottomWrapper
import com.mobile.pablo.uicomponents.data.NoteTopWrapper
import com.mobile.pablo.uicomponents.ui.note.NoteBottomBar
import com.mobile.pablo.uicomponents.ui.note.NoteTopBar
import com.mobile.pablo.uicomponents.ui.note.TextCanvas
import com.mobile.pablo.uicomponents.ui.theme.NoteBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NoteScreen(
    navigator: DestinationsNavigator,
    noteId: Int,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val fullNote = viewModel.note.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(
        key1 = fullNote
    ) {
        viewModel.downloadNote(noteId)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.NoteBackground),
        constraintSet = constraints
    ) {
        NoteTopBar(
            modifier = Modifier
                .layoutId(ID_NOTE_TOP_BAR)
                .fillMaxWidth(),
            noteTopWrapper = NoteTopWrapper(
                onBackItem =
                { (context as? ComponentActivity)?.onBackPressedDispatcher?.onBackPressed() },
                onShareItem = { viewModel.shareNote() },
                onDoneItem = { fullNote.value?.let { viewModel.saveNote(it) } }
            )
        )
        viewModel.saveNote(
            TextCanvas(
                modifier = Modifier
                    .layoutId(ID_TEXT_CANVAS)
                    .fillMaxWidth(),
                note = fullNote.value,
                noteId = noteId
            )
        )
        NoteBottomBar(
            modifier = Modifier
                .layoutId(ID_NOTE_BOTTOM_BAR)
                .fillMaxWidth(),
            noteBottomWrapper = NoteBottomWrapper(
                {},
                {},
                {},
                {}
            )
        )
    }
}

private val constraints = ConstraintSet {
    val noteTopBar = createRefFor(ID_NOTE_TOP_BAR)
    val textCanvas = createRefFor(ID_TEXT_CANVAS)
    val noteBottomBar = createRefFor(ID_NOTE_BOTTOM_BAR)

    constrain(noteTopBar) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(textCanvas) {
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
private const val ID_NOTE_TOP_BAR = "note_top_bar"
private const val ID_TEXT_CANVAS = "text_canvas"
private const val ID_NOTE_BOTTOM_BAR = "note_bottom_bar"