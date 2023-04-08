package com.mobile.pablo.storage.source.home

import com.mobile.pablo.core.model.home.PreviewNoteDTO

interface PreviewNoteDataStorage {

    suspend fun getNotes(): List<PreviewNoteDTO?>

    suspend fun deleteNote(noteId: Int)

    suspend fun insertNote(previewNoteDTO: PreviewNoteDTO?)

    suspend fun clearNotes()
}