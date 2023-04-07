package com.mobile.pablo.storage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["idFullNote","idDescription"],
    foreignKeys = [
        ForeignKey(
            entity =  FullNoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["idFullNote"]
        ), ForeignKey(
            entity = NoteLineEntity::class,
            parentColumns = ["id"],
            childColumns = ["idDescription"]
        )
    ]
)
data class FullNoteWithDescriptionEntity(
    var idFullNote: Long,
    @ColumnInfo(index = true)
    var idDescription: Long
)