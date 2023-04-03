package com.mobile.pablo.uicomponents.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.data.NoteBottomWrapper
import com.mobile.pablo.uicomponents.ui.theme.CTA

@Composable
fun NoteBottomBar(modifier: Modifier = Modifier, noteBottomWrapper : NoteBottomWrapper) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { noteBottomWrapper.onCheckboxItem() },
            contentAlignment = Alignment.Center,
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
                .clickable { noteBottomWrapper.onCameraItem },
            contentAlignment = Alignment.Center,
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
                .clickable { noteBottomWrapper.onPin },
            contentAlignment = Alignment.Center,
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
                .clickable { noteBottomWrapper.onNew },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.write),
                contentDescription = stringResource(id = R.string.write),
                tint = Theme.colors.CTA
            )
        }
    }
}