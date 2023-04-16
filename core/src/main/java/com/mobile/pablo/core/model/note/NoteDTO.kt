package com.mobile.pablo.core.model.note

import java.util.Date

data class NoteDTO(
    val id: Int,
    val title: String,
    val date: Date,
    val description: List<NoteLineDTO>
)
