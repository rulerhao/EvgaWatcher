package com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteGpuProducts (
    private val repository: FavoriteGpuProductRepository
) {
    suspend operator fun invoke(
    ): List<GpuProduct> {
        return repository.getFavoriteGpuProducts()
    }
}