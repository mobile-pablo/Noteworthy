package com.mobile.pablo.domain.data.note

import java.util.Date

data class Note(
    val id: Int,
    val title: String,
    val date: Date,
    val description: List<NoteLine?>
)
