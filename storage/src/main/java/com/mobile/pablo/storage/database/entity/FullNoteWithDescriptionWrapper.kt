package com.mobile.pablo.storage.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FullNoteWithDescriptionWrapper(
    @Embedded
    var fullNoteEntity: FullNoteEntity,
    @Relation(
        entity = NoteLineEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = (
                Junction(
                    value = FullNoteWithDescriptionEntity::class,
                    parentColumn = "idFullNote",
                    entityColumn = "idDescription"
                )
                )
    )
    var noteLineEntityList: List<NoteLineEntity>
)