package com.mobile.pablo.iosnotes.nav

import com.mobile.pablo.note.NoteNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object RootNavGraph : NavGraphSpec {

    override val route = "root"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute = NoteNavGraph

    override val nestedNavGraphs = listOf(
        NoteNavGraph
    )
}