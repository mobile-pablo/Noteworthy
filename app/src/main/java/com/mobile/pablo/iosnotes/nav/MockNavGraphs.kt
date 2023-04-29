package com.mobile.pablo.iosnotes.nav

import com.mobile.pablo.addnote.destinations.FakeAddNoteScreenDestination
import com.mobile.pablo.editnote.destinations.FakeEditNoteScreenDestination
import com.mobile.pablo.note.destinations.FakeNoteScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object MockNavGraphs {

    val main = object : NavGraphSpec {
        override val route = "main"

        override val startRoute = FakeNoteScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            FakeNoteScreenDestination,
            FakeAddNoteScreenDestination,
            FakeEditNoteScreenDestination
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
