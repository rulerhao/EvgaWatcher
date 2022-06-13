package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.products_screen.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent

sealed class FavoriteProductsScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): FavoriteProductsScreenEvent()
    data class ToggleFavoriteGpuProduct(val gpuProduct: GpuProduct): FavoriteProductsScreenEvent()
}
