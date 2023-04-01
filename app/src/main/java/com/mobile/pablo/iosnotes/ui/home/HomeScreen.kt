package com.mobile.pablo.iosnotes.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.iosnotes.ui.destinations.CreateScreenDestination
import com.mobile.pablo.iosnotes.ui.destinations.NoteScreenDestination
import com.mobile.pablo.uicomponents.ui.home.PreviewNoteItem
import com.mobile.pablo.uicomponents.ui.home.TopHomeBar
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.mobile.pablo.uicomponents.ui.util.Theme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.*

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.homeBackground)
            .padding(horizontal = Theme.spacing.spacing_6)
    ) {
        Column {
            TopHomeBar()
            PreviewNoteItem(
                previewNote = PreviewNote(
                    0,
                    "Wtorek | 30.03",
                    Date(42L),
                    "Lorem ipsum dolet sit amet"
                ),
                onClick = { navigateToNote(navigator) }
            )
            LazyColumn(content = {})
        }
    }
}

fun navigateToNote(navigator: DestinationsNavigator) {
    navigator.navigate(NoteScreenDestination)
}

fun navigateToCreate(navigator: DestinationsNavigator) {
    navigator.navigate(CreateScreenDestination)
}