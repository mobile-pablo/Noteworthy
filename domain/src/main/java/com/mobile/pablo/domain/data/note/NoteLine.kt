package com.mobile.pablo.domain.data.note

data class NoteLine(
    val id: Int,
    val fullNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
