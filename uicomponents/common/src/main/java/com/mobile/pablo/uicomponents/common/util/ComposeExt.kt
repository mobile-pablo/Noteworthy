package com.mobile.pablo.uicomponents.common.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource

@Composable
fun Modifier.testTag(
    @androidx.annotation.StringRes resId: Int
) = testTag(stringResource(id = resId))
