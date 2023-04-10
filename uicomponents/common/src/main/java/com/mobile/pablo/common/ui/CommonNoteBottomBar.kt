package com.mobile.pablo.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mobile.pablo.common.R
import com.mobile.pablo.common.data.NoteBottomWrapper
import com.mobile.pablo.common.theme.CTA
import com.mobile.pablo.common.theme.spacing

@Composable
fun CommonNoteBottomBar(modifier: Modifier = Modifier, noteBottomWrapper : NoteBottomWrapper) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { noteBottomWrapper.onCheckboxItem() }
                .padding(vertical = Theme.spacing.spacing_6),
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
                .clickable { noteBottomWrapper.onCameraItem }
                .padding(vertical = Theme.spacing.spacing_6),
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
                .clickable { noteBottomWrapper.onPin }
                .padding(vertical = Theme.spacing.spacing_6),
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
                .clickable { noteBottomWrapper.onNew }
                .padding(vertical = Theme.spacing.spacing_6),
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