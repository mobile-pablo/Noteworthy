package com.mobile.pablo.editnote

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.launchAsync
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.usecase.note.NoteUseCase
import com.mobile.pablo.uicomponents.common.util.StringRes.INSERT_NOTE
import com.mobile.pablo.uicomponents.common.util.StringRes.INTERNET_ISSUE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private var getNoteUseCase: NoteUseCase.GetNote,
    private var insertNoteUseCase: NoteUseCase.InsertNote,
    private val deleteNoteUseCase: NoteUseCase.DeleteNote
) : ViewModel(), EditNoteInterface {

    private var saveJob: Job? = null

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Default)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    override fun saveNote(note: Note) {
        saveJob?.cancel()
        saveJob = launchAsync {
            val noteResult = insertNoteUseCase(note)
            val viewState = noteResult.run {
                if (isSuccessful && data != null) {
                    ViewState.Message(INSERT_NOTE)
                } else ViewState.Message(INTERNET_ISSUE)
            }

            _viewState.emit(viewState)
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
    class Message(val message: String) : ViewState()
    object Default : ViewState()
}
