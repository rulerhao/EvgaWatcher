package com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository

class GetFavoriteGpuProductByName (
    private val repository: FavoriteGpuProductRepository
) {
        suspend operator fun invoke(name: String): GpuProduct? {
            return repository.getFavoriteGpuProductByName(name)
        }
    }