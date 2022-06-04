package com.rulhouse.evgawatcher.presentation.screen

sealed class MainScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): MainScreenEvent()
}
