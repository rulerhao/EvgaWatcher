package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct

sealed class FavoriteProductsScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): FavoriteProductsScreenEvent()
    data class ToggleFavoriteGpuProduct(val gpuProduct: GpuProduct): FavoriteProductsScreenEvent()
}
