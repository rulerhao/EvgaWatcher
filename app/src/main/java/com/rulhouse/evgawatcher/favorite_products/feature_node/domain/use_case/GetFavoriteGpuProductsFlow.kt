package com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteGpuProductsFlow (
    private val repository: FavoriteGpuProductRepository
) {
    operator fun invoke(
    ): Flow<List<GpuProduct>> {
        return repository.getFavoriteGpuProductsFlow()
    }
}