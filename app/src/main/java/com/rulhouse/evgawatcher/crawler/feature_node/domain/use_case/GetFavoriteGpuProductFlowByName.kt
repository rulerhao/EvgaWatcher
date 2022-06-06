package com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteGpuProductFlowByName (
    private val repository: FavoriteGpuProductRepository
) {
    operator fun invoke(name: String): Flow<GpuProduct> {
        return repository.getFavoriteGpuProductFlowByName(name)
    }
}