package com.mobile.pablo.uicomponents.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.ui.theme.Text
import com.mobile.pablo.uicomponents.ui.theme.font
import com.mobile.pablo.uicomponents.ui.theme.spacing
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun TopHomeBar(
    modifier: Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        Text(
            text = stringResource(id = R.string.all_notes),
            fontSize = Theme.font.font_28,
            color = Theme.colors.Text
        )
        Spacer(
            modifier = Modifier.height(
                Theme.spacing.spacing_14
            )
        )
        SearchBar(textState)
        Spacer(
            modifier = Modifier.height(
                Theme.spacing.spacing_14
            )
        )
    }
}