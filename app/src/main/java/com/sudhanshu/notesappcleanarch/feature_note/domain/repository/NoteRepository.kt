package com.sudhanshu.notesappcleanarch.feature_note.domain.repository

import com.sudhanshu.notesappcleanarch.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<Note>>

    suspend fun getNote(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}