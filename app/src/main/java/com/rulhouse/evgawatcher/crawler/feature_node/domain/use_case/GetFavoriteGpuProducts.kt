package com.oucare.bbt_oucare.feature_node.domain.use_case

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteGpuProducts (
    private val repository: FavoriteGpuProductRepository
) {
    operator fun invoke(
    ): Flow<List<GpuProduct>> {
        return repository.getFavoriteGpuProducts()
    }
}