package com.mobile.pablo.domain.usecase.note

import com.mobile.pablo.domain.data.note.FullNote
import com.mobile.pablo.domain.mapper.note.FullNoteMapper
import com.mobile.pablo.storage.source.note.FullNoteDataStorage
import javax.inject.Inject

sealed class FullNoteUseCase {

    class GetFullNote @Inject constructor(
        private val fullNoteMapper: FullNoteMapper,
        private val fullNoteDataStorage: FullNoteDataStorage
    ) {

        suspend operator fun invoke(noteId: Int): FullNote? {
            val response = fullNoteDataStorage.getNote(noteId)
            return fullNoteMapper.map(response)
        }

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

        suspend operator fun invoke(noteId: Int) =
            fullNoteDataStorage.deleteNote(noteId)
    }

    class ClearFullNotes @Inject constructor(
        private val fullNoteDataStorage: FullNoteDataStorage,
    ) {

        suspend operator fun invoke() =
            fullNoteDataStorage.clearNotes()
    }
}