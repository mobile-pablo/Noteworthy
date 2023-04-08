package com.mobile.pablo.storage.database.entity

import androidx.room.Embedded
import androidx.room.Relation

internal data class NoteWithDescriptionEntity(
    @Embedded
    val noteEntity: NoteEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentNoteId"
    )
    val noteLineEntityList: List<NoteLineEntity?>
)