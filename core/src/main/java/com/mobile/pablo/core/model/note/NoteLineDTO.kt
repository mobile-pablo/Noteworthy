package com.mobile.pablo.core.model.note

data class NoteLineDTO(
    val id: Int? = null,
    val fullNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = ""
)
