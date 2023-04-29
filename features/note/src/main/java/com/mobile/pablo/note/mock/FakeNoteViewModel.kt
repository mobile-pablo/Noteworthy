package com.mobile.pablo.note.mock

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.utils.launchAsync
import com.mobile.pablo.domain.data.note.Note
import com.mobile.pablo.domain.data.note.NoteLine
import com.mobile.pablo.note.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class FakeNoteViewModel @Inject constructor() : ViewModel() {

    private var deleteJob: Job? = null
    private var insertJob: Job? = null

    val notes: Flow<List<Note?>> = flow { emit(MOCK_NOTE_LIST) }

    private val _viewState: Channel<ViewState> = Channel()
    val viewState: Flow<ViewState> = _viewState.receiveAsFlow()

    fun deleteNote(noteId: Int) {
        deleteJob?.cancel()
        deleteJob = launchAsync {
            MOCK_NOTE_LIST.removeAt(noteId)
        }
    }

    fun insertEmptyNote() {
        insertJob?.cancel()
        insertJob = launchAsync {
            val lastId = MOCK_NOTE_LIST.last().id + 1
            MOCK_NOTE_LIST.add(Note(id = lastId))
            _viewState.send(ViewState.InsertSuccessful(lastId.toLong()))
        }
    }

    fun setEmptyNote(noteId: Long?) {
        insertJob?.cancel()
        insertJob = launchAsync {
            _viewState.send(ViewState.InsertSuccessful(noteId))
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
            ),
            NoteLine(
                1,
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
            ),
            NoteLine(
                3,
                1,
                true,
                "This is a checkbox"
            )
        )
    )
)
