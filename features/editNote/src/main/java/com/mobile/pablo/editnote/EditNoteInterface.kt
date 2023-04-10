package com.mobile.pablo.editnote

import com.mobile.pablo.domain.data.note.Note

interface EditNoteInterface {

    fun downloadNote(noteId: Int)

    fun createEmptyNoteLine(parentNoteId: Int)

    fun saveNote(note: Note)

    fun pinNote(noteId: Int)

    fun shareNote()
}