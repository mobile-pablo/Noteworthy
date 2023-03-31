package com.mobile.pablo.storage.source.home

import com.mobile.pablo.core.model.home.PreviewNoteDTO
import com.mobile.pablo.storage.database.entity.PreviewNoteEntity

interface PreviewNoteDataStorage {

    suspend fun getNotes(): List<PreviewNoteDTO?>

    suspend fun deleteNote(noteId: String)

    suspend fun insertNote(previewNoteDTO: PreviewNoteDTO?)

    suspend fun clearNotes()
}