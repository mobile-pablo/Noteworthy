package com.mobile.pablo.macrobenchmark.screens

import com.mobile.pablo.macrobenchmark.ext.getResByString
import com.mobile.pablo.macrobenchmark.ext.getResFromId
import com.mobile.pablo.macrobenchmark.ext.getString
import com.mobile.pablo.uicomponents.common.R

class AddNoteBenchmarkScreen {

    val views = AddNoteBenchmarkScreenViews

    fun getNoteLineAtPosition(position: Int) = getResFromId("${views.noteLine}$position")

    fun clickNoteLineAtPosition(position: Int) = getNoteLineAtPosition(position).click()

    object AddNoteBenchmarkScreenViews {
        val title = getResByString(R.string.title_id)
        val noteLine = getString(R.string.note_line_id)
    }
}
