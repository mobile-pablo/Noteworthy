package com.mobile.pablo.iosnotes.screens

import androidx.annotation.StringRes
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mobile.pablo.iosnotes.MainActivity

@OptIn(ExperimentalTestApi::class)
open class BasicTestScreen constructor(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

    fun getString(@StringRes id: Int) = rule.activity.getString(id)

    fun waitForTag(
        tag: String
    ) = rule.waitUntilAtLeastOneExists(
        hasTestTag(tag),
        WAIT_VIEW_MILLIS
    )

    fun waitForTag(
        @StringRes tag: Int
    ) = rule.waitUntilAtLeastOneExists(
        hasTestTag(getString(tag)),
        WAIT_VIEW_MILLIS
    )

    fun onTag(
        tag: String,
        useUnmergedTree: Boolean = false
    ): SemanticsNodeInteraction = rule.onNodeWithTag(
        tag,
        useUnmergedTree = useUnmergedTree
    )

    fun onTag(
        @StringRes tag: Int,
        useUnmergedTree: Boolean = false
    ): SemanticsNodeInteraction = rule.onNodeWithTag(
        getString(tag),
        useUnmergedTree = useUnmergedTree
    )

    fun onUnmergedTreeWithTag(@StringRes tag: Int): SemanticsNodeInteraction =
        onTag(
        tag,
        useUnmergedTree = true
    )

    fun onUnmergedTreeWithTag(tag: String): SemanticsNodeInteraction =
        onTag(
            tag,
            useUnmergedTree = true
        )

    companion object {

        const val WAIT_VIEW_MILLIS = 5000L
    }
}
