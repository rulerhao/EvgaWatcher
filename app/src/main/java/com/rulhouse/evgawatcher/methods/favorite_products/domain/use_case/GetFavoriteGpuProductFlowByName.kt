package com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteGpuProductFlowByName (
    private val repository: FavoriteGpuProductRepository
) {
    operator fun invoke(name: String): Flow<GpuProduct> {
        return repository.getFavoriteGpuProductFlowByName(name)
    }
}