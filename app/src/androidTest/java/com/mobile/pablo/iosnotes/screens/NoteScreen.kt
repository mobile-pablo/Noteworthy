package com.mobile.pablo.iosnotes.screens

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.espresso.action.ViewActions.click
import com.mobile.pablo.macrobenchmark.ext.waitForViewIsDisplayed
import com.mobile.pablo.uicomponents.note.R

class NoteScreen{

    val views = NoteScreenViews

    fun clickAddItemBtn() {
        waitForViewIsDisplayed(views.addItemBtn)
            .perform(click())
    }

    object NoteScreenViews {

        val addItemBtn = R.string.test_id_add_note_btn
    }
}
