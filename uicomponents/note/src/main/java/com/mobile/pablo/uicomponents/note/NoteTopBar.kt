package com.mobile.pablo.uicomponents.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme as Theme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING
import com.mobile.pablo.uicomponents.common.theme.Text
import com.mobile.pablo.uicomponents.common.theme.font
import com.mobile.pablo.uicomponents.common.theme.spacing
import com.mobile.pablo.uicomponents.common.ui.SearchBar

@Composable
fun NoteTopBar(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val textState = remember { mutableStateOf(TextFieldValue(EMPTY_STRING)) }
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
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            state = textState
        )
        Spacer(
            modifier = Modifier.height(
                Theme.spacing.spacing_14
            )
        )
    }
}
