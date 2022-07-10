package com.rulhouse.evgawatcher.crawler.crawler_repository.domain.use_cases

import com.rulhouse.evgawatcher.crawler.crawler_repository.domain.repository.CrawlerRepositoryRepository
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.InvalidFavoriteGpuProductException
import kotlinx.coroutines.flow.Flow

class GetCrawlerRepositoryFlow (
    private val repository: CrawlerRepositoryRepository
) {
    suspend operator fun invoke(): Flow<List<GpuProduct>> {
        return repository.getProductsFlow()
    }
}