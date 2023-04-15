package com.mobile.pablo.addnote

import com.mobile.pablo.domain.data.note.Note

interface AddNoteInterface {

    fun downloadNote(noteId: Int)

    fun createEmptyNoteLine(parentNoteId: Int)

    fun saveNote(note: Note)

    fun pinNote(noteId: Int)

    fun shareNote()
}
