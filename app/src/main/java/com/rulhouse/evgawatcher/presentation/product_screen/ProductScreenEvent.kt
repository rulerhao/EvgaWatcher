package com.rulhouse.evgawatcher.presentation.product_screen

sealed class ProductScreenEvent {
    object ToggleFavoriteButton: ProductScreenEvent()
}
