package com.rulhouse.evgawatcher.notification_gpu_product_change.repository

import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference

interface GetDifferentProductsRepository {
    suspend fun getDifference(): List<ProductsDifference>
}