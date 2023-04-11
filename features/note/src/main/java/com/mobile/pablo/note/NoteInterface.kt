package com.mobile.pablo.note

interface NoteInterface {

    fun deleteNote(noteId: Int)

    fun insertEmptyNote()

    fun setEmptyNote(noteId : Long?)
}