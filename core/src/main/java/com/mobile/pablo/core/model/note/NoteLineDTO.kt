package com.mobile.pablo.core.model.note

import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING

data class NoteLineDTO(
    val id: Int,
    val parentNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = EMPTY_STRING
)
