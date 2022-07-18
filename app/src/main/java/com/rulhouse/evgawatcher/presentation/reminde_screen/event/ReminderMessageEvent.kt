package com.rulhouse.evgawatcher.presentation.reminde_screen.event

sealed class ReminderMessageEvent {
    object OnGetAll: ReminderMessageEvent()
    data class OnGetIt(val index: Int): ReminderMessageEvent()
    object OnRefresh: ReminderMessageEvent()
}
