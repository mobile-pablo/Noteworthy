package com.mobile.pablo.core.model.home

import java.util.Date

data class PreviewNoteDTO(
    val id: Int? = null,
    val title: String,
    val date: Date,
    val description: String
)
