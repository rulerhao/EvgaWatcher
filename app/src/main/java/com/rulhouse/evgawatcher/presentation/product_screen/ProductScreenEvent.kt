package com.rulhouse.evgawatcher.presentation.product_screen

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct

sealed class ProductScreenEvent {
    object ToggleFavoriteButton: ProductScreenEvent()
}
