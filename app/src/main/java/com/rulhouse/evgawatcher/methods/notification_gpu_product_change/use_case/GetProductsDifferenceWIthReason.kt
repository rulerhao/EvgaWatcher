package com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case

import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.repository.GetDifferentProductsRepository

class GetProductsDifferenceWIthReason(
    private val repository: GetDifferentProductsRepository
) {
    suspend operator fun invoke(): List<ProductsDifferenceWithReason> {
        return repository.getProductsDifferenceWithReason()
    }
}