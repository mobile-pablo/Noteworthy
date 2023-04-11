package com.mobile.pablo.uicomponents.common.util

import com.mobile.pablo.domain.data.note.Note
import java.util.Date

object EmptyObjects  {
    val EMPTY_NOTE = Note(
        id = 0,
        title = "",
        date = Date(),
        description = listOf()
    )
}