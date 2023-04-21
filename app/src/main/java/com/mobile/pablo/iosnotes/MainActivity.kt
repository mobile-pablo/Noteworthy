package com.mobile.pablo.iosnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.iosnotes.ui.PermissionGranted
import com.mobile.pablo.iosnotes.ui.PermissionRequest
import com.mobile.pablo.uicomponents.common.theme.IOSNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IOSNotesTheme {

                val cameraPermission = rememberPermissionState(
                    android.Manifest.permission.CAMERA
                )

                if (cameraPermission.status.isGranted) {
                    PermissionGranted()
                } else {
                    PermissionRequest(cameraPermission)
                }
            }
        }
    }
}
