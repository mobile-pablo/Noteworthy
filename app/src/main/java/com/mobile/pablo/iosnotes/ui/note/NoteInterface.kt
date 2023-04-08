package com.mobile.pablo.iosnotes.ui.note

import com.mobile.pablo.domain.data.note.Note

interface NoteInterface {

    fun downloadNote(noteId: Int)

    fun createEmptyNoteLine(parentNoteId: Int)

    fun saveNote(note: Note)

    fun pinNote(noteId: Int)

    fun shareNote()
}