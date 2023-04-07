package com.mobile.pablo.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "note_line"
)
data class NoteLineEntity(
    @PrimaryKey
    val id: String,
    val fullNoteId: String,
    val isCheckbox: Boolean = false,
    val noteText: String = "0"
) {

    override fun toString(): String {
        return "$id,$fullNoteId,$isCheckbox,$noteText"
    }
}