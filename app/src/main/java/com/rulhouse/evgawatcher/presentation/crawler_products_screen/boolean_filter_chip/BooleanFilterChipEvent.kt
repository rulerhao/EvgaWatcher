package com.rulhouse.evgawatcher.presentation.crawler_products_screen.boolean_filter_chip

sealed class BooleanFilterChipEvent {
    object OnShowingOutOfStockChanged: BooleanFilterChipEvent()
    object OnPriceAscendingChanged: BooleanFilterChipEvent()
    object OnShowingNoPriceChanged: BooleanFilterChipEvent()
}
