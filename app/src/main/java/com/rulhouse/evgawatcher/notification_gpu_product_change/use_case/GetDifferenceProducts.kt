package com.rulhouse.evgawatcher.notification_gpu_product_change.use_case

import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.notification_gpu_product_change.repository.GetDifferentProductsRepository

class GetDifferenceProducts(
    private val repository: GetDifferentProductsRepository
) {
    suspend operator fun invoke(): List<ProductsDifference> {
        return repository.getDifference()
    }
}