package com.oucare.bbt_oucare.feature_node.domain.use_case

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.repository.FavoriteGpuProductRepository

class GetFavoriteGpuProduct(
    private val repository: FavoriteGpuProductRepository
) {
    suspend operator fun invoke(id: Int): GpuProduct? {
        return repository.getFavoriteGpuProductById(id)
    }
}