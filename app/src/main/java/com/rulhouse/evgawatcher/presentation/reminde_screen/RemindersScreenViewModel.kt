package com.rulhouse.evgawatcher.presentation.reminde_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RemindersScreenViewModel @Inject constructor(): ViewModel() {
    fun onEvent(event: RemindersScreenEvent) {
        when(event) {
            RemindersScreenEvent.OnAddClick -> {

            }
        }
    }
}