package com.sudhanshu.notesappcleanarch.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sudhanshu.notesappcleanarch.ui.theme.BabyBlue
import com.sudhanshu.notesappcleanarch.ui.theme.LightGreen
import com.sudhanshu.notesappcleanarch.ui.theme.RedOrange
import com.sudhanshu.notesappcleanarch.ui.theme.RedPink
import com.sudhanshu.notesappcleanarch.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val color: Int,
    val timeStamp: Long,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen)
    }
}

class InvalidNoteException(message: String) : Exception(message)
