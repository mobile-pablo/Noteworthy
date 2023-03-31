package com.mobile.pablo.domain.usecase.home

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.domain.mapper.home.PreviewNoteMapper
import com.mobile.pablo.storage.source.home.PreviewNoteDataStorage
import javax.inject.Inject

sealed class PreviewNoteUseCase {

    class GetPreviewNotes @Inject constructor(
        private val previewNoteMapper: PreviewNoteMapper,
        private val previewNoteDataStorage: PreviewNoteDataStorage
    ) {

        suspend operator fun invoke(): DataTransfer<List<PreviewNote?>> =
            DataTransfer(
                previewNoteDataStorage.getNotes()
                    .map(previewNoteMapper::map)
            )
    }

    class InsertPreviewNote @Inject constructor(
        private val previewNoteMapper: PreviewNoteMapper,
        private val previewNoteDataStorage: PreviewNoteDataStorage,
    ) {

        suspend operator fun invoke(note: PreviewNote) {
            val response = previewNoteMapper.map(note)
            previewNoteDataStorage.insertNote(response)
        }
    }

    class DeletePreviewNote @Inject constructor(
        private val previewNoteDataStorage: PreviewNoteDataStorage,
    ) {

        suspend operator fun invoke(noteId: String) = previewNoteDataStorage.deleteNote(noteId)
    }

    class ClearPreviewNotes @Inject constructor(
        private val previewNoteDataStorage: PreviewNoteDataStorage,
    ) {

        suspend operator fun invoke() = previewNoteDataStorage.clearNotes()
    }
}