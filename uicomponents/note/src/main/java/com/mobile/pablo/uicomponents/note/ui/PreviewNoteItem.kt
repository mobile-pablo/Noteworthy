package com.mobile.pablo.uicomponents.note.ui

import DateUtils.dayMonthYearFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.uicomponents.note.R
import com.mobile.pablo.uicomponents.note.util.testTag
import com.mobile.pablo.uicomponents.note.theme.*
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun PreviewNoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: () -> Unit,
    onPin: () -> Unit,
    onDelete: () -> Unit
) {

    val pinAction = SwipeAction(icon = painterResource(id = R.drawable.pin),
        background = Theme.colors.SwipeStart,
        onSwipe = { onPin() })

    val deleteAction = SwipeAction(icon = painterResource(id = R.drawable.trash),
        background = Theme.colors.SwipeEnd,
        isUndo = true,
        onSwipe = { onDelete() })

    SwipeableActionsBox(
        startActions = listOf(pinAction),
        endActions = listOf(deleteAction),
        modifier = modifier.clip(RoundedCornerShape(Theme.spacing.spacing_6))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Theme.spacing.spacing_6))
                .clickable(onClick = onClick)
                .background(Theme.colors.PreviewNoteBackground)
                .padding(
                    vertical = Theme.spacing.spacing_16,
                    horizontal = Theme.spacing.spacing_20
                )
        ) {
            Column {
                Text(
                    text = note.title,
                    fontSize = Theme.font.font_15,
                    color = Theme.colors.Text,
                    modifier = Modifier.testTag(R.string.test_id_title)
                )

                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth(),
                    constraintSet = previewConstraints
                ) {
                    Text(
                        text = dayMonthYearFormat(note.date),
                        fontSize = Theme.font.font_9,
                        color = Theme.colors.Text,
                        modifier = Modifier
                            .layoutId(ID_DATE_TEXT)
                            .padding(end = Theme.spacing.spacing_10)
                            .testTag(R.string.test_id_date)
                    )

                    note.description.let {
                        if (it.isNotEmpty()) {
                            Text(
                                text = it[0].noteText,
                                fontSize = Theme.font.font_9,
                                color = Theme.colors.Text,
                                modifier = Modifier
                                    .layoutId(ID_DESCRIPTION)
                                    .padding(horizontal = Theme.spacing.spacing_12)
                                    .testTag(R.string.test_id_description),
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

private val previewConstraints = ConstraintSet {
    val dateText = createRefFor(ID_DATE_TEXT)
    val description = createRefFor(ID_DESCRIPTION)

    constrain(dateText) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
    }

    constrain(description) {
        top.linkTo(parent.top)
        start.linkTo(dateText.end)
        height = Dimension.fillToConstraints
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

// Layout ids
private const val ID_DATE_TEXT = "dateText"
private const val ID_DESCRIPTION = "description"
