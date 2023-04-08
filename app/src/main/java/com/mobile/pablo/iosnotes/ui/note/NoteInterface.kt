package com.mobile.pablo.iosnotes.ui.note

import com.mobile.pablo.domain.data.note.FullNote

interface NoteInterface {

    fun downloadNote(noteId: Int)

    fun saveNote(fullNote: FullNote)

    fun pinNote(noteId: Int)

    fun shareNote()
}