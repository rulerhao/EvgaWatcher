package com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case


import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.data.InvalidFavoriteGpuProductException
import com.rulhouse.evgawatcher.methods.favorite_products.domain.repository.FavoriteGpuProductRepository

class AddFavoriteGpuProduct (
    private val repository: FavoriteGpuProductRepository
) {
    @Throws(InvalidFavoriteGpuProductException::class)
    suspend operator fun invoke(gpuProduct: GpuProduct) {
        repository.insertFavoriteGpuProduct(gpuProduct)
    }
}