package com.mobile.pablo.uicomponents.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING
import com.mobile.pablo.uicomponents.common.theme.*
import com.mobile.pablo.uicomponents.common.util.topRectBorder
import kotlinx.coroutines.launch
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun NoteBottomBar(
    amountNotes: Int,
    modifier: Modifier = Modifier,
    onClickNewNote: () -> Unit
) {

    val buttonScope = rememberCoroutineScope()

    var notesText by remember { mutableStateOf(EMPTY_STRING) }

    LaunchedEffect(amountNotes) {
        notesText = "$amountNotes "
        notesText += if (amountNotes < 2) "note" else "notes"
    }
    ConstraintLayout(
        modifier = modifier
            .background(Theme.colors.HomeBottomBarBackground)
            .topRectBorder(
                brush = SolidColor(Theme.colors.PreviewLine)
            ),
        constraintSet = homeBottomConstraints
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
                color = Theme.colors.Text,
            )
        }

        Box(modifier = Modifier.layoutId(ID_ADD_USER)) {
            IconButton(
                onClick = {
                    buttonScope.launch { onClickNewNote() }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.write),
                    contentDescription = stringResource(id = R.string.write),
                    tint = Theme.colors.CTA,
                    modifier = Modifier.size(Theme.spacing.spacing_24)
                )
            }
        }
    }
}

private val homeBottomConstraints = ConstraintSet {
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