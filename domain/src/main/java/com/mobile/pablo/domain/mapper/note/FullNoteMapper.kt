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

    fun map(fullNote: FullNoteWrapper?): FullNoteWrapperDTO? = fullNote?.run {
        FullNoteWrapperDTO(
            title,
            date,
            fullDescription.map(noteLineMapper::map)
        )
    }
}