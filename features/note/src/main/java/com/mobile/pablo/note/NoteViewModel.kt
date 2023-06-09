package com.mobile.pablo.note

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.launchAsync
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import com.mobile.pablo.uicomponents.note.util.StringRes.DELETE_SUCCESSFUL
import com.mobile.pablo.uicomponents.note.util.StringRes.INTERNET_ISSUE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class NoteViewModel @Inject constructor(
    getNotesUseCase: NoteUseCase.GetNotes,
    private val deleteNoteUseCase: NoteUseCase.DeleteNote,
    private val insertEmptyNoteUseCase: NoteUseCase.InsertEmptyNote
) : ViewModel(), NoteInterface {

    private var deleteJob: Job? = null
    private var insertJob: Job? = null
    private val NOTE_DEBOUNCE_MILLIS = 100L

    val notes: Flow<List<Note?>> = getNotesUseCase().debounce(NOTE_DEBOUNCE_MILLIS)

    private val _viewState: Channel<ViewState> = Channel()
    val viewState: Flow<ViewState> = _viewState.receiveAsFlow()

    override fun deleteNote(noteId: Int) {
        deleteJob?.cancel()
        deleteJob = launchAsync {
            val noteResult = deleteNoteUseCase(noteId)
            val viewState = noteResult.run {
                if (isSuccessful && data != null) {
                    ViewState.Message(DELETE_SUCCESSFUL)
                } else ViewState.Message(INTERNET_ISSUE)
            }

            _viewState.send(viewState)
        }
    }

    override fun insertEmptyNote() {
        insertJob?.cancel()
        insertJob = launchAsync {
            val noteResult = insertEmptyNoteUseCase()
            val viewState = noteResult.run {
                if (isSuccessful && data != null) {
                    ViewState.InsertSuccessful(data)
                } else ViewState.Message(INTERNET_ISSUE)
            }

            _viewState.send(viewState)
        }
    }

    override fun setEmptyNote(noteId: Long?) {
        insertJob?.cancel()
        insertJob = launchAsync {
            _viewState.send(ViewState.InsertSuccessful(noteId))
        }
    }
}

sealed class ViewState {
    object Default : ViewState()
    class InsertSuccessful(val noteId: Long?) : ViewState()
    class Message(val message: String) : ViewState()
}
