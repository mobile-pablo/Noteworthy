package com.mobile.pablo.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "notes"
)
internal data class FullNoteEntity(
    @PrimaryKey
    val id : String,
    val title : String,
    val date : Date,
    val fullDescription : String
)