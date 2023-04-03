package com.mobile.pablo.uicomponents.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.data.NoteTopWrapper
import com.mobile.pablo.uicomponents.ui.theme.CTA
import com.mobile.pablo.uicomponents.ui.theme.spacing
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun NoteTopBar(
    modifier: Modifier = Modifier,
    noteTopWrapper: NoteTopWrapper
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth(),
        constraintSet = constraints
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(ID_GO_BACK)
                .clickable { noteTopWrapper.onBackItem() }
                .padding(vertical = Theme.spacing.spacing_6),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.list),
                contentDescription = stringResource(id = R.string.list),
                tint = Theme.colors.CTA
            )
            Spacer(modifier = Modifier.width(Theme.spacing.spacing_8))

            // For now we don't have folders, In future title will be passed here via Wrapper
            Text(text = stringResource(id = R.string.all_notes))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { noteTopWrapper.onShareItem() }
                .padding(vertical = Theme.spacing.spacing_6),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = stringResource(id = R.string.share),
                tint = Theme.colors.CTA
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { noteTopWrapper.onDoneItem }
                .padding(vertical = Theme.spacing.spacing_6),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = stringResource(id = R.string.done))
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