package com.mobile.pablo.iosnotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.SingleLiveEvent
import com.mobile.pablo.domain.data.home.PreviewNote
import com.mobile.pablo.domain.usecase.home.PreviewNoteUseCase
import com.mobile.pablo.domain.usecase.note.FullNoteUseCase
import com.mobile.pablo.iosnotes.util.launch
import com.mobile.pablo.uicomponents.util.StringRes.INTERNET_ISSUE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPreviewNotes: PreviewNoteUseCase.GetPreviewNotes,
    private val deletePreviewNote: PreviewNoteUseCase.DeletePreviewNote,
    private val deleteFullNote: FullNoteUseCase.DeleteFullNote,
    private val insertEmptyFullNote: FullNoteUseCase.InsertEmptyNote
) : ViewModel(), HomeInterface {

    private var getNotesJob: Job? = null
    private var deleteNoteJob: Job? = null

    private val _previewNotes: MutableStateFlow<List<PreviewNote?>?> = MutableStateFlow(null)
    val previewNotes: StateFlow<List<PreviewNote?>?> = _previewNotes.asStateFlow()

    private val _emptyNoteId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val emptyNoteId: StateFlow<Long?> = _emptyNoteId.asStateFlow()

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    init {
        downloadNotes()
    }

    override fun downloadNotes() {
        getNotesJob?.cancel()
        getNotesJob = launch {
            val noteResult = getPreviewNotes()

            _viewState.value = noteResult.run {
                if (isSuccessful && data != null) {
                    _previewNotes.emit(data)
                    ViewState.DownloadSuccessful
                } else ViewState.Error(INTERNET_ISSUE)
            }
        }
    }

    override fun deleteNote(noteId: Int) {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            deletePreviewNote(noteId)
            deleteFullNote(noteId)
        }
    }

    override fun insertEmptyNote() {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            _emptyNoteId.emit(insertEmptyFullNote())
        }
    }
}

sealed class ViewState {
    object DownloadSuccessful : ViewState()
    class Error(val message: String) : ViewState()
}