package com.rulhouse.evgawatcher.presentation.products_screen.event

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct

sealed class ProductsScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): ProductsScreenEvent()
    data class ClickGpuProduct(val gpuProduct: GpuProduct): ProductsScreenEvent()
    data class ToggleFavoriteGpuProduct(val gpuProduct: GpuProduct): ProductsScreenEvent()
    object OnShowingOutOfStockChanged: ProductsScreenEvent()
    object OnPriceAscendingChanged: ProductsScreenEvent()
    object OnShowingNoPriceChanged: ProductsScreenEvent()
}
