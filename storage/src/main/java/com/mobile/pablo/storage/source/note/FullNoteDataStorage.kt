package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.FullNoteDTO

interface FullNoteDataStorage {

    suspend fun getNotes(): List<FullNoteDTO?>

    suspend fun deleteNote(noteId: String)

    suspend fun insertNote(fullNoteEntity: FullNoteDTO?)

    suspend fun clearNotes()
}