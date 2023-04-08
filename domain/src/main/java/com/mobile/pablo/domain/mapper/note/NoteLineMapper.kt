package com.mobile.pablo.domain.mapper.note

import com.mobile.pablo.core.model.note.NoteLineDTO
import com.mobile.pablo.domain.data.note.NoteLine
import javax.inject.Inject

class NoteLineMapper @Inject constructor() {

    fun map(dto: NoteLineDTO?): NoteLine? = dto?.run {
        NoteLine(
            id,
            parentNoteId,
            isCheckbox,
            noteText
        )
    }

    fun map(noteLine: NoteLine?): NoteLineDTO? = noteLine?.run {
        NoteLineDTO(
            id,
            parentNoteId,
            isCheckbox,
            noteText
        )
    }
}