package com.mobile.pablo.iosnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.mobile.pablo.iosnotes.ui.NavGraphs
import com.mobile.pablo.uicomponents.ui.theme.IOSNotesTheme
import com.mobile.pablo.uicomponents.ui.util.Theme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IOSNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Theme.colors.homeBackground
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}