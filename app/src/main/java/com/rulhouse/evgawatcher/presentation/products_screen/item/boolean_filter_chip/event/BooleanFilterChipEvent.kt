package com.rulhouse.evgawatcher.presentation.products_screen.item.boolean_filter_chip.event

sealed class BooleanFilterChipEvent {
    object OnShowingOutOfStockChanged: BooleanFilterChipEvent()
    object OnPriceAscendingChanged: BooleanFilterChipEvent()
    object OnShowingNoPriceChanged: BooleanFilterChipEvent()
}
