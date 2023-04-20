package com.mobile.pablo.addnote

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobile.pablo.uicomponents.common.data.NoteBottomWrapper
import com.mobile.pablo.uicomponents.common.data.NoteTopWrapper
import com.mobile.pablo.uicomponents.common.theme.NoteBackground
import com.mobile.pablo.uicomponents.common.ui.CommonNoteBottomBar
import com.mobile.pablo.uicomponents.common.ui.CommonNoteTopBar
import com.mobile.pablo.uicomponents.common.ui.TextCanvas
import com.mobile.pablo.uicomponents.common.util.EmptyObjects.EMPTY_NOTE
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import androidx.compose.material.MaterialTheme as Theme

data class AddNoteScreenNavArgs(
    val noteId: Int
)

@Destination(navArgsDelegate = AddNoteScreenNavArgs::class)
@Composable
fun AddNoteScreen(
    addNoteScreenNavArgs: AddNoteScreenNavArgs,
    viewModel: AddNoteViewModel = hiltViewModel()
) {
    val noteId = addNoteScreenNavArgs.noteId
    viewModel.downloadNote(noteId)

    val scope = rememberCoroutineScope()
    val note = viewModel.note.collectAsStateWithLifecycle(EMPTY_NOTE)
    val emptyNoteLineId = viewModel.emptyNoteLineId.collectAsStateWithLifecycle()
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value
    val context = LocalContext.current
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(
        key1 = viewState,
        key2 = note
    ) {
        when (viewState) {
            is ViewState.SaveSuccessful -> {
                (context as? ComponentActivity)?.onBackPressedDispatcher?.onBackPressed()
            }

            is ViewState.Message -> {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = viewState.message
                )
            }

            is ViewState.Default -> {}
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.NoteBackground),
        constraintSet = constraints
    ) {
        note.value?.let { note ->
            val updatedNote = TextCanvas(
                modifier = Modifier
                    .layoutId(ID_TEXT_CANVAS)
                    .fillMaxWidth(),
                note = note,
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

            CommonNoteTopBar(
                modifier = Modifier
                    .layoutId(ID_ADD_NOTE_TOP_BAR)
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
                            viewModel.saveNote(updatedNote).also {
                                (context as? ComponentActivity)
                                    ?.onBackPressedDispatcher?.onBackPressed()
                            }
                        }
                    }
                )
            )

            CommonNoteBottomBar(
                modifier = Modifier
                    .layoutId(ID_ADD_NOTE_BOTTOM_BAR)
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
}

fun createEmptyNoteLine(
    noteId: Int,
    viewModel: AddNoteViewModel,
    emptyNoteLineId: Long
): Long {
    viewModel.createEmptyNoteLine(noteId)
    return emptyNoteLineId
}

private val constraints = ConstraintSet {
    val noteTopBar = createRefFor(ID_ADD_NOTE_TOP_BAR)
    val textCanvas = createRefFor(ID_TEXT_CANVAS)
    val noteBottomBar = createRefFor(ID_ADD_NOTE_BOTTOM_BAR)

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
private const val ID_ADD_NOTE_TOP_BAR = "add_note_top_bar"
private const val ID_TEXT_CANVAS = "text_canvas"
private const val ID_ADD_NOTE_BOTTOM_BAR = "dd_note_bottom_bar"
