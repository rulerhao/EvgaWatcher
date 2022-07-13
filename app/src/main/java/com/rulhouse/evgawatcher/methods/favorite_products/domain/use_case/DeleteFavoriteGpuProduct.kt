package com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.repository.FavoriteGpuProductRepository

class DeleteFavoriteGpuProduct (
    private val repository: FavoriteGpuProductRepository
) {
    suspend operator fun invoke(gpuProduct: GpuProduct) {
        repository.deleteFavoriteGpuProduct(gpuProduct)
    }
}