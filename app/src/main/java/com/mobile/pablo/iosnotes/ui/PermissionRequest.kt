package com.mobile.pablo.iosnotes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequest(permissionState: PermissionState) {
    val scope = rememberCoroutineScope()
    Column {
        val textToShow = if (permissionState.status.shouldShowRationale) {
            "The camera is important for this app. Please grant the permission."
        } else {
            "Camera permission required for this feature to be available. " +
                "Please grant the permission"
        }
        Text(textToShow)
        Button(onClick = { scope.launch { permissionState.launchPermissionRequest() } }) {
            Text("Request permission")
        }
    }
}
