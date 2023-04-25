package com.mobile.pablo.iosnotes.screens

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule

open class BasicTestScreen constructor(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
) {

    fun getString(@StringRes id: Int) = rule.activity.getString(id)

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
}
