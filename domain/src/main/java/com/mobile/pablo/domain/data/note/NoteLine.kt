package com.mobile.pablo.domain.data.note

data class NoteLine(
    val id: String,
    val fullNoteId: String,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
