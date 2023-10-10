package com.sudhanshu.notesappcleanarch.feature_note.presentation.util

sealed class Screens(val route: String){
    object NotesScreen: Screens("notes_screen")
    object AddEditNoteScreen: Screens("add_edit_notes_screen")
}
