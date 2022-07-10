package com.rulhouse.evgawatcher.favorite_products.domain.use_case

import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.domain.repository.FavoriteGpuProductRepository

class GetFavoriteGpuProducts (
    private val repository: FavoriteGpuProductRepository
) {
    suspend operator fun invoke(
    ): List<GpuProduct> {
        return repository.getFavoriteGpuProducts()
    }
}