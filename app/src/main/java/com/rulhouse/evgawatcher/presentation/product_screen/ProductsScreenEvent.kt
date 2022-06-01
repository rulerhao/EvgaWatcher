package com.rulhouse.evgawatcher.presentation.product_screen

import com.rulhouse.evgawatcher.GpuProduct

sealed class ProductsScreenEvent {
    data class ClickGpuProduct(val gpuProduct: GpuProduct): ProductsScreenEvent()
}
