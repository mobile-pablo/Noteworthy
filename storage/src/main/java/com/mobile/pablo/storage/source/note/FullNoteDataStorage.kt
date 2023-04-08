package com.mobile.pablo.storage.source.note

import com.mobile.pablo.core.model.note.FullNoteDTO

interface FullNoteDataStorage {

    suspend fun getNote(noteId: Int): FullNoteDTO?

    suspend fun deleteNote(noteId: Int)

    suspend fun insertNote(dto: FullNoteDTO?)

    suspend fun clearNotes()
}