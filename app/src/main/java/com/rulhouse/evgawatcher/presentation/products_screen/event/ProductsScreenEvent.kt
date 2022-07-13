package com.rulhouse.evgawatcher.presentation.products_screen.event

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct

sealed class ProductsScreenEvent {
    data class OnCollapseColumnStateChanged(val index: Int): ProductsScreenEvent()
    data class ClickGpuProduct(val gpuProduct: GpuProduct): ProductsScreenEvent()
    data class ToggleFavoriteGpuProduct(val gpuProduct: GpuProduct): ProductsScreenEvent()
    object OnFilterStateChanged: ProductsScreenEvent()
    object OnShowingOutOfStockChanged: ProductsScreenEvent()
    object OnPriceAscendingChanged: ProductsScreenEvent()
    data class  OnPriceSortedChanged(val isOn: Boolean): ProductsScreenEvent()
    object OnShowingNoPriceChanged: ProductsScreenEvent()
}
