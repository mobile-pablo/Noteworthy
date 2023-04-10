package com.mobile.pablo.uicomponents.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.mobile.pablo.uicomponents.common.R
import com.mobile.pablo.uicomponents.common.data.NoteTopWrapper
import com.mobile.pablo.uicomponents.common.theme.CTA
import com.mobile.pablo.uicomponents.common.theme.spacing
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun CommonNoteTopBar(
    modifier: Modifier = Modifier,
    noteTopWrapper: NoteTopWrapper
) {
    ConstraintLayout(
        modifier = modifier
            .padding(horizontal = Theme.spacing.spacing_4),
        constraintSet = constraints
    ) {
        Row(
            modifier = Modifier
                .layoutId(ID_GO_BACK)
                .clickable { noteTopWrapper.onBackItem() }
                .padding(vertical = Theme.spacing.spacing_7, horizontal = Theme.spacing.spacing_4),
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = stringResource(id = R.string.list),
                tint = Theme.colors.CTA
            )
            Spacer(modifier = Modifier.width(Theme.spacing.spacing_8))

            // For now we don't have folders, In future title will be passed here via Wrapper
            Text(
                text = stringResource(id = R.string.all_notes),
                color = Theme.colors.CTA,
                fontWeight = FontWeight.Medium
            )
        }

        Box(
            modifier = Modifier
                .layoutId(ID_SHARE)
                .clickable { noteTopWrapper.onShareItem() }
                .padding(Theme.spacing.spacing_7),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.share),
                contentDescription = stringResource(id = R.string.share),
                tint = Theme.colors.CTA
            )
        }

        Box(
            modifier = Modifier
                .layoutId(ID_DONE)
                .clickable { noteTopWrapper.onDoneItem() }
                .padding(Theme.spacing.spacing_7),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.done),
                color = Theme.colors.CTA,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private val constraints = ConstraintSet {
    val goBack = createRefFor(ID_GO_BACK)
    val share = createRefFor(ID_SHARE)
    val done = createRefFor(ID_DONE)

    constrain(goBack) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
    }

    constrain(done) {
        top.linkTo(parent.top)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }

    constrain(share) {
        top.linkTo(parent.top)
        end.linkTo(done.start)
        bottom.linkTo(parent.bottom)
    }
}

private const val ID_GO_BACK = "go_back"
private const val ID_SHARE = "share"
private const val ID_DONE = "done"