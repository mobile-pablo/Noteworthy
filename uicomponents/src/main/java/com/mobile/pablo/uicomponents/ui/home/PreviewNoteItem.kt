package com.mobile.pablo.uicomponents.ui.home

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.uicomponents.ui.theme.*
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun PreviewNoteItem(
    previewNote: PreviewNote,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.spacing.spacing_6))
            .background(Theme.colors.PreviewNoteBackground)
            .padding(
                vertical = Theme.spacing.spacing_16,
                horizontal = Theme.spacing.spacing_20
            )
            .clickable(onClick = onClick)
    ) {
        Column {
            Text(
                text = previewNote.title,
                fontSize = Theme.font.font_15,
                color = Theme.colors.Text
            )

            ConstraintLayout(
                modifier = Modifier.fillMaxWidth(),
                constraintSet = previewConstraints
            ) {
                Text(
                    text = dayMonthYearFormat(previewNote.date),
                    fontSize = Theme.font.font_9,
                    color = Theme.colors.Text,
                    modifier = Modifier
                        .layoutId(ID_DATE_TEXT)
                        .padding(end = Theme.spacing.spacing_20)
                )

                Text(
                    text = previewNote.description,
                    fontSize = Theme.font.font_9,
                    color = Theme.colors.Text,
                    modifier = Modifier
                        .layoutId(ID_DESCRIPTION)
                        .padding(
                            start = Theme.spacing.spacing_20,
                            end = Theme.spacing.spacing_20
                        ),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
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
        end.linkTo(parent.end)
        start.linkTo(dateText.end)
        height = Dimension.wrapContent
        bottom.linkTo(parent.bottom)
    }
}

// Layout ids
private const val ID_DATE_TEXT = "dateText"
private const val ID_DESCRIPTION = "description"