package com.mobile.pablo.iosnotes.ui.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.pablo.uicomponents.ui.theme.Text
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CreateScreen(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Theme.spacing.spacing_6)
    ) {
        Greeting("Create Screen")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = Theme.colors.Text)
}