package com.mobile.pablo.uicomponents.ui.note

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.ui.theme.Text
import androidx.compose.material.MaterialTheme as Theme

/**
I have spent over 5hours trying to do that make that
TextField not scrollable and limited dynamicly but I couldnt find solution.
Same with trying AndroidView with xmls. Only w
 */

@Composable
fun NoteField(
    modifier: Modifier = Modifier
) : TextFieldValue {
    val focusManager = LocalFocusManager.current

    var text by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = {}
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(stringResource(id = R.string.search)) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Theme.colors.Text,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
    }

    return text
}

