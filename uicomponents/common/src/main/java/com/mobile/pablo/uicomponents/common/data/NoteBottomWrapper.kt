package com.mobile.pablo.uicomponents.common.data

data class NoteBottomWrapper(
    val onCheckboxItem : () -> Unit,
    val onCameraItem : () -> Unit,
    val onPin : () -> Unit,
    val onNew : () -> Unit,
)
