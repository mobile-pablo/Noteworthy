package com.mobile.pablo.iosnotes.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.iosnotes.ui.destinations.CreateScreenDestination
import com.mobile.pablo.iosnotes.ui.destinations.NoteScreenDestination
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.ui.home.BottomHomeBar
import com.mobile.pablo.uicomponents.ui.home.PreviewNoteItem
import com.mobile.pablo.uicomponents.ui.home.TopHomeBar
import com.mobile.pablo.uicomponents.ui.theme.HomeBackground
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.Date

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.HomeBackground)
            .padding(horizontal = Theme.spacing.spacing_6)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = homeConstraints
        ) {
            TopHomeBar(Modifier.layoutId(ID_TOP_HOME_BAR))
            LazyColumn(
                modifier = Modifier.layoutId(ID_PREVIEW_NOTE_LISTS),
                content = {
                    item {
                        PreviewNoteItem(
                            previewNote = PreviewNote(
                                0,
                                "Wtorek | 30.03",
                                Date(42L),
                                stringResource(id = R.string.lorem_ipsum)
                            ),
                            onClick = { navigateToNote(navigator) }
                        )
                    }
                }
            )
            BottomHomeBar(
                5,
                modifier = Modifier.layoutId(ID_BOTTOM_HOME_BAR),
                onClickNewNote = { navigateToCreate(navigator) }
            )
        }
    }
}

fun navigateToNote(navigator: DestinationsNavigator) {
    navigator.navigate(NoteScreenDestination)
}

fun navigateToCreate(navigator: DestinationsNavigator) {
    navigator.navigate(CreateScreenDestination)
}

private val homeConstraints = ConstraintSet {

    val topHomeBar = createRefFor(ID_TOP_HOME_BAR)
    val previewNoteLists = createRefFor(ID_PREVIEW_NOTE_LISTS)
    val bottomHomeBar = createRefFor(ID_BOTTOM_HOME_BAR)

    constrain(topHomeBar) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(previewNoteLists) {
        top.linkTo(topHomeBar.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        height = Dimension.fillToConstraints
        bottom.linkTo(bottomHomeBar.top)
    }

    constrain(bottomHomeBar) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

// Layout ids
private const val ID_TOP_HOME_BAR = "topHomeBar"
private const val ID_PREVIEW_NOTE_LISTS = "previewNoteLists"
private const val ID_BOTTOM_HOME_BAR = "bottomHomeBar"