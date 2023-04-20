package com.mobile.pablo.note.mock

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.launch
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.note.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

@HiltViewModel
class FakeNoteViewModel @Inject constructor() : ViewModel() {

    private var deleteNoteJob: Job? = null

    val notes: Flow<List<Note?>> = flow { MOCK_NOTE_LIST }

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Default)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    fun deleteNote(noteId: Int) {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            MOCK_NOTE_LIST.removeAt(noteId)
        }
    }

    fun insertEmptyNote() {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            val lastId = MOCK_NOTE_LIST.last().id + 1
            MOCK_NOTE_LIST.add(Note(id = lastId))
        }
    }

    fun setEmptyNote(noteId: Long?) {
        deleteNoteJob?.cancel()
        deleteNoteJob = launch {
            _viewState.emit(ViewState.InsertSuccessful(noteId))
        }
    }
}

val MOCK_NOTE_LIST = mutableListOf(
    Note(
        id = 0,
        title = "Hello",
        date = Date(),
        description = listOf(
            NoteLine(
                0,
                0,
                true,
                "This is a checkbox"
            )
        )
    ),
    Note(
        id = 1,
        title = "Hello",
        date = Date(),
        description = listOf(
            NoteLine(
                2,
                1,
                true,
                "This is a checkbox"
            )
        )
    )
)
