package com.mobile.pablo.iosnotes.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mobile.pablo.iosnotes.nav.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun PermissionGranted() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root
        )
    }
}
