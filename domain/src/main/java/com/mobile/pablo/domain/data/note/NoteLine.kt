package com.mobile.pablo.domain.data.note

data class NoteLine(
    val id: Int = 0,
    val parentNoteId: Int = 0,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
