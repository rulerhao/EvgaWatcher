package com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.repository.FavoriteGpuProductRepository

class GetFavoriteGpuProductByName (
    private val repository: FavoriteGpuProductRepository
) {
        suspend operator fun invoke(name: String): GpuProduct? {
            return repository.getFavoriteGpuProductByName(name)
        }
    }