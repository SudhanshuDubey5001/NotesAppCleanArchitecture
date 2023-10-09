package com.sudhanshu.notesappcleanarch.feature_note.domain.utils

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)

    //in order to change the order, we copy all the title, Date and color to new order provided
    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is Color -> Color(orderType)
            is Date -> Date(orderType)
            is Title -> Title(orderType)
        }
    }
}
