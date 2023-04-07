package com.mobile.pablo.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "preview_notes"
)
internal data class PreviewNoteEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val date: Date,
    val description: String
)