package com.oucare.bbt_oucare.feature_node.domain.use_case


import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.data.InvalidFavoriteGpuProductException
import com.rulhouse.evgawatcher.crawler.feature_node.domain.repository.FavoriteGpuProductRepository

class AddFavoriteGpuProduct (
    private val repository: FavoriteGpuProductRepository
) {
    @Throws(InvalidFavoriteGpuProductException::class)
    suspend operator fun invoke(gpuProduct: GpuProduct) {
        repository.insertFavoriteGpuProduct(gpuProduct)
    }
}