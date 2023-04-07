package com.mobile.pablo.iosnotes.ui.note

import com.mobile.pablo.domain.data.note.FullNote

interface NoteInterface {

    fun downloadNote(noteId: String)

    fun saveNote(fullNote: FullNote)

    fun pinNote(noteId: String)

    fun shareNote()
}