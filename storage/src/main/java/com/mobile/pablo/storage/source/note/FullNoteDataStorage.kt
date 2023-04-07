package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.FullNoteDTO

interface FullNoteDataStorage {

    suspend fun getNote(noteId: String): FullNoteDTO?

    suspend fun deleteNote(noteId: String)

    suspend fun insertNote(dto: FullNoteDTO?)

    suspend fun clearNotes()
}