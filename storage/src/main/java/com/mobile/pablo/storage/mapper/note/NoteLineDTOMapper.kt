package com.mobile.pablo.storage.mapper.note

import com.mobile.pablo.core.model.note.NoteLineDTO
import com.mobile.pablo.storage.database.entity.NoteLineEntity
import javax.inject.Inject

internal class NoteLineDTOMapper @Inject constructor() {

    fun map(entity: NoteLineEntity?): NoteLineDTO? = entity?.run {
        NoteLineDTO(
            id,
            fullNoteId,
            isCheckbox,
            noteText
        )
    }

    fun map(dto: NoteLineDTO?): NoteLineEntity? = dto?.run {
        NoteLineEntity(
            id,
            fullNoteId,
            isCheckbox,
            noteText
        )
    }
}