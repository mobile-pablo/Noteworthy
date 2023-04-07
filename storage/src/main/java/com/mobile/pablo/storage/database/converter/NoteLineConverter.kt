package com.mobile.pablo.storage.database.converter

import androidx.room.TypeConverter
import com.mobile.pablo.storage.database.entity.NoteLineEntity

object NoteLineConverter {

    @TypeConverter
    fun fromNoteLine(entity: NoteLineEntity?): String? = entity?.toString()

    @TypeConverter
    fun fromString(string: String): NoteLineEntity {
        val items = string.split(",")
            return NoteLineEntity(
                items[0].toInt(),
                items[1].toBoolean(),
                items[2],
        )
    }
}