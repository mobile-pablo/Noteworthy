package com.mobile.pablo.iosnotes.ui.note

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.uicomponents.ui.note.NoteTopBar
import com.mobile.pablo.uicomponents.ui.note.TextCanvas
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.material.MaterialTheme as Theme

@Destination
@Composable
fun NoteScreen(
    navigator: DestinationsNavigator,
    previewNote: PreviewNote?
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(Theme.spacing.spacing_6),
        constraintSet = constraints
    ) {
        NoteTopBar()
        TextCanvas()
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