package com.rulhouse.evgawatcher.presentation.product_screen

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct

sealed class ProductScreenEvent {
    data class ClickGpuProduct(val gpuProduct: GpuProduct): ProductScreenEvent()
    data class ToggleFavoriteGpuProduct(val gpuProduct: GpuProduct): ProductScreenEvent()
    data class OnCollapseColumnStateChanged(val index: Int): ProductScreenEvent()
}
