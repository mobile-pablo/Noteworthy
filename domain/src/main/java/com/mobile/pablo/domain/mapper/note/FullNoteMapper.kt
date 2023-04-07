package com.mobile.pablo.domain.mapper.note

import com.mobile.pablo.core.model.note.FullNoteDTO
import com.mobile.pablo.domain.data.note.FullNote
import javax.inject.Inject

class FullNoteMapper @Inject constructor(
   private val noteLineMapper: NoteLineMapper
) {

    fun map(dto: FullNoteDTO?): FullNote? = dto?.run {
        FullNote(
            id,
            title,
            date,
            fullDescription.map(noteLineMapper::map)
        )
    }

    fun map(fullNote: FullNote?): FullNoteDTO? = fullNote?.run {
        FullNoteDTO(
            id,
            title,
            date,
            fullDescription.map(noteLineMapper::map)
        )
    }
}