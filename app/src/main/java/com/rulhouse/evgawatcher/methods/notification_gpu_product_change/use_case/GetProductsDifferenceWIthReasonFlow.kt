package com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case

import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.repository.GetDifferentProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProductsDifferenceWIthReasonFlow(
    private val repository: GetDifferentProductsRepository
) {
    suspend operator fun invoke(): Flow<List<ProductsDifferenceWithReason>> {
        return repository.getProductsDifferenceWithReasonFlow()
    }
}