package com.mobile.pablo.iosnotes.nav

import com.mobile.pablo.addnote.destinations.AddNoteScreenDestination
import com.mobile.pablo.editnote.destinations.EditNoteScreenDestination
import com.mobile.pablo.note.destinations.NoteScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object NavGraphs {

    val main = object : NavGraphSpec {
        override val route = "main"

        override val startRoute = NoteScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            NoteScreenDestination,
            AddNoteScreenDestination,
            EditNoteScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = main
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(main)
    }
}