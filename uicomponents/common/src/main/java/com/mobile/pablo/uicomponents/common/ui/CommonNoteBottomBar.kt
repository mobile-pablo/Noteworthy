package com.mobile.pablo.uicomponents.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.uicomponents.common.R
import com.mobile.pablo.uicomponents.common.theme.CTA
import com.mobile.pablo.uicomponents.common.theme.spacing
import androidx.compose.material.MaterialTheme as Theme

typealias onCameraItem = () -> Unit
typealias onCheckboxItem = () -> Unit
typealias onPinItem = () -> Unit
typealias onNewItem = () -> Unit

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CommonNoteBottomBar(
    modifier: Modifier = Modifier,
    onCamera: onCameraItem,
    onCheckbox: onCheckboxItem,
    onPin: onPinItem,
    onNew: onNewItem
) {
    val cameraPermission = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { onCheckbox() }
                .padding(vertical = Theme.spacing.spacing_6),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.list),
                contentDescription = stringResource(id = R.string.list),
                tint = Theme.colors.CTA
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable {
                    cameraPermission.launchPermissionRequest()

                    if (cameraPermission.status.isGranted)
                        onCamera()
                }
                .padding(vertical = Theme.spacing.spacing_6),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.image),
                contentDescription = stringResource(id = R.string.image),
                tint = Theme.colors.CTA
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { onPin() }
                .padding(vertical = Theme.spacing.spacing_6),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pin),
                contentDescription = stringResource(id = R.string.pin),
                tint = Theme.colors.CTA
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { onNew() }
                .padding(vertical = Theme.spacing.spacing_6),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.write),
                contentDescription = stringResource(id = R.string.write),
                tint = Theme.colors.CTA
            )
        }
    }
}
