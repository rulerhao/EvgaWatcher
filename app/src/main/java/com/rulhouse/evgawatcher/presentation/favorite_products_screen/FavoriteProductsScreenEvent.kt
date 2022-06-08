package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent

sealed class FavoriteProductsScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): FavoriteProductsScreenEvent()
}
