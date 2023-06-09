package com.mobile.pablo.uicomponents.common.ui

import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.input.ImeAction
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.uicomponents.common.theme.Text
import androidx.compose.material.MaterialTheme as Theme

typealias onEmptyNoteLineItem = () -> Long

@Composable
fun NoteField(
    modifier: Modifier = Modifier,
    noteLine: NoteLine,
    hasCheckbox: Boolean = false,
    placeHolder: String = EMPTY_STRING,
    onEmptyNoteLine: onEmptyNoteLineItem
): NoteLine {

    val focusManager = LocalFocusManager.current

    var noteText by remember { mutableStateOf(noteLine.noteText) }
    var isCheckbox by remember { mutableStateOf(noteLine.isCheckbox) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hasCheckbox) {
            Checkbox(
                checked = isCheckbox,
                onCheckedChange = { isCheckbox = it }
            )
        }

        TextField(
            value = noteText,
            onValueChange = { noteText = it },
            placeholder = { Text(placeHolder) },
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

    // Problem to zapisuje sie automatycznie bez on click w kazdym z notefield
    return NoteLine(
        id = onEmptyNoteLine().toInt(),
        isCheckbox = isCheckbox,
        noteText = noteText,
        parentNoteId = noteLine.parentNoteId
    )
}

@Composable
fun NoteField(
    modifier: Modifier = Modifier,
    noteLine: NoteLine,
    hasCheckbox: Boolean = false,
    placeHolder: String = EMPTY_STRING
): NoteLine {

    val focusManager = LocalFocusManager.current

    var noteText by remember { mutableStateOf(noteLine.noteText) }
    var isCheckbox by remember { mutableStateOf(noteLine.isCheckbox) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hasCheckbox) {
            Checkbox(
                checked = isCheckbox,
                onCheckedChange = { isCheckbox = it }
            )
        }

        TextField(
            value = noteText,
            onValueChange = { noteText = it },
            placeholder = { Text(placeHolder) },
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

    return NoteLine(
        id = noteLine.id,
        isCheckbox = isCheckbox,
        noteText = noteText,
        parentNoteId = noteLine.parentNoteId
    )
}

@Composable
fun NoteField(
    modifier: Modifier = Modifier,
    title: String,
    placeHolder: String
): String {
    val focusManager = LocalFocusManager.current

    var noteText by remember { mutableStateOf(title) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = noteText,
            onValueChange = { noteText = it },
            placeholder = { Text(placeHolder) },
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

    return noteText
}
