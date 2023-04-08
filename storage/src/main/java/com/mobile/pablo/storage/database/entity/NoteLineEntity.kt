package com.mobile.pablo.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "note_line"
)
data class NoteLineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val fullNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = "0"
) {

    override fun toString(): String {
        return "$id,$fullNoteId,$isCheckbox,$noteText"
    }
}