package com.rulhouse.evgawatcher.presentation.products_screen.boolean_filter_chip

import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent

sealed class BooleanFilterChipEvent {
    object OnShowingOutOfStockChanged: BooleanFilterChipEvent()
    object OnPriceAscendingChanged: BooleanFilterChipEvent()
    object OnShowingNoPriceChanged: BooleanFilterChipEvent()
}
