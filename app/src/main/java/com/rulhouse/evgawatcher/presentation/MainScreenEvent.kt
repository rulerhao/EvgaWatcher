package com.rulhouse.evgawatcher.presentation

sealed class MainScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): MainScreenEvent()
}
