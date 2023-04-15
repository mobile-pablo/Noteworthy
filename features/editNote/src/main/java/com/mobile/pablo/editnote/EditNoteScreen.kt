package com.mobile.pablo.editnote

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.uicomponents.common.data.NoteBottomWrapper
import com.mobile.pablo.uicomponents.common.data.NoteTopWrapper
import com.mobile.pablo.uicomponents.common.theme.NoteBackground
import com.mobile.pablo.uicomponents.common.ui.CommonNoteBottomBar
import com.mobile.pablo.uicomponents.common.ui.CommonNoteTopBar
import com.mobile.pablo.uicomponents.common.ui.TextCanvas
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

data class EditNoteScreenNavArgs(
    val note: Note
)

@Destination(navArgsDelegate = EditNoteScreenNavArgs::class)
@Composable
fun EditNoteScreen(
    editNoteScreenNavArgs: EditNoteScreenNavArgs,
    viewModel: EditNoteViewModel = hiltViewModel()
) {
    val note = editNoteScreenNavArgs.note
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.NoteBackground),
        constraintSet = constraints
    ) {
        val updatedNote = TextCanvas(
            modifier = Modifier
                .layoutId(ID_TEXT_CANVAS)
                .fillMaxWidth(),
            note = note,
            noteId = note.id
        )

        CommonNoteTopBar(
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
