package com.oucare.bbt_oucare.feature_node.domain.use_case

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.domain.repository.FavoriteGpuProductRepository

class GetFavoriteGpuProduct(
    private val repository: FavoriteGpuProductRepository
) {
    suspend operator fun invoke(id: Int): GpuProduct? {
        return repository.getFavoriteGpuProductById(id)
    }
}