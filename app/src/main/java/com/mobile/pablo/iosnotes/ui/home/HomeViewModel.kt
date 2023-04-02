package com.mobile.pablo.iosnotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPreviewNotes: PreviewNoteUseCase.GetPreviewNotes,
    private val deletePreviewNote: PreviewNoteUseCase.DeletePreviewNote,
    private val deleteFullNote: FullNoteUseCase.DeleteFullNote
) : ViewModel() {

    private var getNotesJob: Job? = null
    private var deleteNoteJob: Job? = null

    private val _previewNotes = MutableLiveData<List<PreviewNote?>>()
    val previewNotes: LiveData<List<PreviewNote?>> = _previewNotes

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    init {
        downloadNotes()
    }

    fun downloadNotes() {
        getNotesJob?.cancel()
        getNotesJob = launch {
            val noteResult = getPreviewNotes()

            _viewState.value = noteResult.run {
                if (isSuccessful && data != null) {
                    _previewNotes.value = data
                    ViewState.DownloadSuccessful
                } else ViewState.Error(INTERNET_ISSUE)
            }
        }
    }

    fun deleteNote(noteId: String) {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            deletePreviewNote(noteId)
            deleteFullNote(noteId)
        }
    }
}

sealed class ViewState {
    object DownloadSuccessful : ViewState()
    class Error(val message: String) : ViewState()
}