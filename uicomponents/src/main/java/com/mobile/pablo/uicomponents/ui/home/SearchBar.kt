package com.mobile.pablo.uicomponents.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.mobile.pablo.uicomponents.ui.theme.font
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.mobile.pablo.uicomponents.ui.util.Theme

@Composable
fun SearchBar(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.spacing.spacing_12)),
        textStyle = TextStyle(
            color = Theme.colors.text,
            fontSize = Theme.font.font_14,
        ),
        leadingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(Theme.spacing.spacing_16)
                            .size(Theme.spacing.spacing_24)
                    )
                }
            } else {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(Theme.spacing.spacing_16)
                        .size(Theme.spacing.spacing_24)
                )
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Theme.colors.text,
            cursorColor = Theme.colors.text,
            leadingIconColor = Theme.colors.text,
            trailingIconColor = Theme.colors.text,
            backgroundColor = Theme.colors.topBarSelectedItemBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}