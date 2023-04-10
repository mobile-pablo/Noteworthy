package com.mobile.pablo.note

interface NoteInterface {

    fun downloadNotes()

    fun deleteNote(noteId: Int)

    fun insertEmptyNote()
}