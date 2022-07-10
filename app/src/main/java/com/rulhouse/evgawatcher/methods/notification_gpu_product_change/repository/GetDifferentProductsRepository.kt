package com.rulhouse.evgawatcher.methods.notification_gpu_product_change.repository

import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference

interface GetDifferentProductsRepository {
    suspend fun getDifference(): List<ProductsDifference>
}