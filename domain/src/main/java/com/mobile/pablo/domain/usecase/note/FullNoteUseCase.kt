package com.mobile.pablo.domain.usecase.note

import com.mobile.pablo.domain.data.home.FullNote
import com.mobile.pablo.domain.mapper.home.FullNoteMapper
import com.mobile.pablo.storage.source.note.FullNoteDataStorage
import javax.inject.Inject

sealed class FullNoteUseCase {

    class GetFullNotes @Inject constructor(
        private val fullNoteMapper: FullNoteMapper,
        private val fullNoteDataStorage: FullNoteDataStorage
    ) {

        suspend operator fun invoke(): List<FullNote?> =
            fullNoteDataStorage.getNotes().map(fullNoteMapper::map)
    }

    class InsertFullNote @Inject constructor(
        private val fullNoteMapper: FullNoteMapper,
        private val fullNoteDataStorage: FullNoteDataStorage,
    ) {

        suspend operator fun invoke(note: FullNote) {
            val response = fullNoteMapper.map(note)
            fullNoteDataStorage.insertNote(response)
        }
    }

    class DeleteFullNote @Inject constructor(
        private val fullNoteDataStorage: FullNoteDataStorage,
    ) {

        suspend operator fun invoke(noteId: String) =
            fullNoteDataStorage.deleteNote(noteId)
    }

    class ClearFullNotes @Inject constructor(
        private val fullNoteDataStorage: FullNoteDataStorage,
    ) {

        suspend operator fun invoke() =
            fullNoteDataStorage.clearNotes()
    }
}