package com.mobile.pablo.iosnotes.screens

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.mobile.pablo.uicomponents.note.R

class NoteScreen constructor(val rule: ComposeContentTestRule) {

    val views = NoteScreenViews

    fun clickAddItemBtn() {
        clickOn(views.addItemBtn)
    }

    object NoteScreenViews {

        val addItemBtn = R.string.test_id_add_note_btn
    }
}
