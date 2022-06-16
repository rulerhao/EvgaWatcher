package com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case


import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.InvalidFavoriteGpuProductException
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository

class AddFavoriteGpuProduct (
    private val repository: FavoriteGpuProductRepository
) {
    @Throws(InvalidFavoriteGpuProductException::class)
    suspend operator fun invoke(gpuProduct: GpuProduct) {
        repository.insertFavoriteGpuProduct(gpuProduct)
    }
}