package com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.domain.repository.FavoriteGpuProductRepository

class GetFavoriteGpuProductByName (
    private val repository: FavoriteGpuProductRepository
) {
        suspend operator fun invoke(name: String): GpuProduct? {
            return repository.getFavoriteGpuProductsByName(name)
        }
    }