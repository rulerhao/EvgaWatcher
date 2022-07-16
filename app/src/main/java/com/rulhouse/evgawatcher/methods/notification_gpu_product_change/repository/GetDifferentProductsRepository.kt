package com.rulhouse.evgawatcher.methods.notification_gpu_product_change.repository

import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import kotlinx.coroutines.flow.Flow

interface GetDifferentProductsRepository {
    suspend fun getDifference(): List<ProductsDifference>

    suspend fun getProductsDifferenceWithReason(): List<ProductsDifferenceWithReason>

    suspend fun getProductsDifferenceWithReasonFlow(): Flow<List<ProductsDifferenceWithReason>>
}