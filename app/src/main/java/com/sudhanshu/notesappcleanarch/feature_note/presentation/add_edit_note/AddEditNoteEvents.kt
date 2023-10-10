package com.sudhanshu.notesappcleanarch.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvents {
    data class EnteredTitle(val value: String): AddEditNoteEvents()
    data class ChangeTitleFocus(val focus: FocusState): AddEditNoteEvents()
    data class EnteredContent(val value: String): AddEditNoteEvents()
    data class ChangeContentFocus(val focus: FocusState): AddEditNoteEvents()

    data class ChangeNoteColor(val color: Int): AddEditNoteEvents()
    object SaveNote: AddEditNoteEvents()
}