package com.rulhouse.evgawatcher.methods.notification_gpu_product_change

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct

data class ProductsDifferenceWithReason(
    val productBeCompare: GpuProduct,
    val productGoCompare: GpuProduct,
    val reason: DifferenceReason
)
