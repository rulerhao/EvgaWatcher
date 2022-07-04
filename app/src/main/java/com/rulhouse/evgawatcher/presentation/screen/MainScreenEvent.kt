package com.rulhouse.evgawatcher.presentation.screen

sealed class MainScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): MainScreenEvent()
    object OnShowingOutOfStockChanged: MainScreenEvent()
    object OnPriceAscendingChanged: MainScreenEvent()
    object OnShowingNoPriceChanged: MainScreenEvent()
}
