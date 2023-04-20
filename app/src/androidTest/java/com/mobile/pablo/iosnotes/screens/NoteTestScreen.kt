package com.mobile.pablo.iosnotes.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mobile.pablo.iosnotes.ext.getString
import com.mobile.pablo.uicomponents.note.R

class NoteTestScreen constructor(
    val rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
) {

    val views = NoteScreenViews(rule)

    fun clickAddItemBtn() {
        rule.onNodeWithTag(views.addItemBtn).performClick()
    }

    class NoteScreenViews(
        rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
    ) {

        val addItemBtn = rule.getString(R.string.test_id_add_note_btn)
    }
}
