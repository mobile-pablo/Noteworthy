package com.mobile.pablo.iosnotes.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.SingleLiveEvent
import com.mobile.pablo.domain.data.note.FullNote
import com.mobile.pablo.domain.usecase.home.PreviewNoteUseCase
import com.mobile.pablo.domain.usecase.note.FullNoteUseCase
import com.mobile.pablo.iosnotes.util.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class NoteViewModel @Inject constructor(
    private var getFullNote: FullNoteUseCase.GetFullNote,
    private var insertFullNote: FullNoteUseCase.InsertFullNote,
    private val deletePreviewNote: PreviewNoteUseCase.DeletePreviewNote,
    private val deleteFullNote: FullNoteUseCase.DeleteFullNote
) : ViewModel(), NoteInterface {

    private var noteJob: Job? = null

    private val _fullNote: MutableStateFlow<FullNote?> = MutableStateFlow(null)
    val fullNote: StateFlow<FullNote?> = _fullNote.asStateFlow()

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    override fun downloadNote(noteId: Int) {
        noteJob?.cancel()
        noteJob = launch {
            val notesResult = getFullNote(noteId)
            _fullNote.emit(notesResult)
        }
    }

    override fun saveNote(fullNote: FullNote) {
        noteJob?.cancel()
        noteJob = launch {
            val result = insertFullNote(fullNote)
        }
    }

    override fun pinNote(noteId: Int) {
        TODO("Not yet implemented")
    }

    override fun shareNote() {
        TODO("Not yet implemented")
    }
}

sealed class ViewState {
    object DownloadSuccessful : ViewState()
    class Error(val message: String) : ViewState()
}