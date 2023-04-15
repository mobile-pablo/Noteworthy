package com.mobile.pablo.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobile.pablo.core.utils.StringConst.EMPTY_STRING

@Entity(
    tableName = "note_line"
)
data class NoteLineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentNoteId: Int,
    val isCheckbox: Boolean = false,
    val noteText: String = EMPTY_STRING
) {

    override fun toString(): String {
        return "$id,$parentNoteId,$isCheckbox,$noteText"
    }
}
