package com.mobile.pablo.uicomponents.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.mobile.pablo.uicomponents.ui.theme.font
import com.mobile.pablo.uicomponents.ui.theme.spacing
import com.mobile.pablo.uicomponents.ui.util.Theme
import kotlinx.coroutines.launch

@Composable
fun BottomHomeBar(
    amountNotes: Int,
    modifier: Modifier,
    onClickNewNote: () -> Unit
) {

    val buttonScope = rememberCoroutineScope()

    var notesText by remember { mutableStateOf("") }

    LaunchedEffect(amountNotes) {
        notesText = "$amountNotes notes"
    }
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colors.topBarSelectedItemBackground)
            .padding(vertical = Theme.spacing.spacing_14),
        constraintSet = bottomHomeConstraints
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(ID_AMOUNT_TITLE),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = notesText,
                fontSize = Theme.font.font_15,
                color = Theme.colors.text,
            )
        }

        Box(modifier = Modifier.layoutId(ID_ADD_USER)) {
            IconButton(
                onClick = {
                    buttonScope.launch { onClickNewNote() }
                }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "New File",
                    tint = Theme.colors.text,
                    modifier = Modifier.size(Theme.spacing.spacing_24)
                )
            }
        }
    }
}

private val bottomHomeConstraints = ConstraintSet {
    val amountTitle = createRefFor(ID_AMOUNT_TITLE)
    val addUser = createRefFor(ID_ADD_USER)

    constrain(amountTitle) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }

    constrain(addUser) {
        top.linkTo(parent.top)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

// Layout ids
private const val ID_AMOUNT_TITLE = "amount_title"
private const val ID_ADD_USER = "add_user"