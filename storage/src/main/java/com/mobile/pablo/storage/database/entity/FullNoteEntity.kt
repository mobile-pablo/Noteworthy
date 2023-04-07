package com.mobile.pablo.storage.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "notes"
)
data class FullNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: Date
)