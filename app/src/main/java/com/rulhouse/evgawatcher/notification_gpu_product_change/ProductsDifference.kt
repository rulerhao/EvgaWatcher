package com.rulhouse.evgawatcher.notification_gpu_product_change

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct

data class ProductsDifference(
    val gpuProduct: GpuProduct,
    val reason: DifferenceReason
)
