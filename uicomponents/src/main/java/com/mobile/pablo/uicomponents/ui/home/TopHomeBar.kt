package com.mobile.pablo.uicomponents.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.ui.theme.font
import com.mobile.pablo.uicomponents.ui.util.Theme

@Composable
fun TopHomeBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.all_notes),
            fontSize = Theme.font.font_28,
            color = Theme.colors.text
        )
    }
}