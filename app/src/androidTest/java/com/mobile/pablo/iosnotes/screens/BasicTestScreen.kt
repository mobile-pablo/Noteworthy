package com.mobile.pablo.iosnotes.screens

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule

@OptIn(ExperimentalTestApi::class)
open class BasicTestScreen constructor(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
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
    ) = rule.onNodeWithTag(
        tag,
        useUnmergedTree = useUnmergedTree
    )

    fun onTag(
        @StringRes tag: Int,
        useUnmergedTree: Boolean = false
    ) = rule.onNodeWithTag(
        getString(tag),
        useUnmergedTree = useUnmergedTree
    )

    fun onUnmergedTreeWithTag(@StringRes tag: Int) = onTag(
        tag,
        useUnmergedTree = true
    )

    fun onUnmergedTreeWithTag(tag: String) = onTag(
        tag,
        useUnmergedTree = true
    )

    companion object {
        const val WAIT_VIEW_MILLIS = 5000L
    }
}