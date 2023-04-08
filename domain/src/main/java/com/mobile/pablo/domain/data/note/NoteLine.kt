package com.mobile.pablo.domain.data.note

data class NoteLine(
    val id: Int? = null,
    val fullNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
