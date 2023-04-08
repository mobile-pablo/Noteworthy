package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.NoteDTO

interface NoteDataStorage {

    suspend fun getNote(noteId: Int): NoteDTO?

    suspend fun getNotes(): List<NoteDTO?>

    suspend fun deleteNote(noteId: Int)

    suspend fun insertNote(dto: NoteDTO?)

    suspend fun insertEmptyNote() : Long

    suspend fun clearNotes()
}