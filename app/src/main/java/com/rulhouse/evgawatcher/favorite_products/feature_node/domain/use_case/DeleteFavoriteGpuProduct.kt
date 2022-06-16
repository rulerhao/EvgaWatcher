package com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository

class DeleteFavoriteGpuProduct (
    private val repository: FavoriteGpuProductRepository
) {
    suspend operator fun invoke(gpuProduct: GpuProduct) {
        repository.deleteFavoriteGpuProduct(gpuProduct)
    }
}