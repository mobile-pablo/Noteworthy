package com.mobile.pablo.editnote

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobile.pablo.uicomponents.data.NoteBottomWrapper
import com.mobile.pablo.uicomponents.data.NoteTopWrapper
import com.mobile.pablo.uicomponents.ui.note.EditNoteBottomBar
import com.mobile.pablo.uicomponents.ui.note.EditNoteTopBar
import com.mobile.pablo.uicomponents.ui.note.TextCanvas
import com.mobile.pablo.uicomponents.ui.theme.NoteBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun EditNoteScreen(
    navigator: DestinationsNavigator,
    noteId: Int,
    viewModel: EditNoteViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val note = viewModel.note.collectAsState()
    val emptyNoteLineId = viewModel.emptyNoteLineId.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(
        key1 = note,
        key2 = emptyNoteLineId
    ) {
        viewModel.downloadNote(noteId)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.NoteBackground),
        constraintSet = constraints
    ) {
        EditNoteTopBar(
            modifier = Modifier
                .layoutId(ID_EDIT_NOTE_TOP_BAR)
                .fillMaxWidth(),
            noteTopWrapper = NoteTopWrapper(
                onBackItem =
                {
                    scope.launch {
                        (context as? ComponentActivity)?.onBackPressedDispatcher?.onBackPressed()
                    }
                },
                onShareItem = { scope.launch { viewModel.shareNote() } },
                onDoneItem = {
                    scope.launch {
                        note.value?.let { viewModel.saveNote(it) }
                        (context as? ComponentActivity)?.onBackPressedDispatcher?.onBackPressed()
                    }
                }
            )
        )

        note.value?.let {
            viewModel.saveNote(
                TextCanvas(
                    modifier = Modifier
                        .layoutId(ID_TEXT_CANVAS)
                        .fillMaxWidth(),
                    note = it,
                    noteId = noteId,
                    createEmptyNoteLine =
                    {
                        createEmptyNoteLine(
                            noteId,
                            viewModel,
                            emptyNoteLineId.value
                        )
                    }
                )
            )
        }

        EditNoteBottomBar(
            modifier = Modifier
                .layoutId(ID_EDIT_NOTE_BOTTOM_BAR)
                .fillMaxWidth(),
            noteBottomWrapper = NoteBottomWrapper(
                { scope.launch {} },
                { scope.launch {} },
                { scope.launch {} },
                { scope.launch {} }
            )
        )
    }
}

fun createEmptyNoteLine(
    noteId: Int,
    viewModel: EditNoteViewModel,
    emptyNoteLineId: Long
): Long {
    viewModel.createEmptyNoteLine(noteId)
    return emptyNoteLineId
}

private val constraints = ConstraintSet {
    val noteTopBar = createRefFor(ID_EDIT_NOTE_TOP_BAR)
    val textCanvas = createRefFor(ID_TEXT_CANVAS)
    val noteBottomBar = createRefFor(ID_EDIT_NOTE_BOTTOM_BAR)

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
private const val ID_EDIT_NOTE_TOP_BAR = "edit_note_top_bar"
private const val ID_TEXT_CANVAS = "text_canvas"
private const val ID_EDIT_NOTE_BOTTOM_BAR = "edit_note_bottom_bar"