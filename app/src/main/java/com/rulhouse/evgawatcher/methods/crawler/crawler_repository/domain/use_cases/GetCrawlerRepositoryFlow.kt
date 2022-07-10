package com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.use_cases

import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.repository.CrawlerRepositoryRepository
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import kotlinx.coroutines.flow.Flow

class GetCrawlerRepositoryFlow (
    private val repository: CrawlerRepositoryRepository
) {
    suspend operator fun invoke(): Flow<List<GpuProduct>> {
        return repository.getProductsFlow()
    }
}