package com.rulhouse.evgawatcher.crawler.crawler_repository.impl

import com.rulhouse.evgawatcher.crawler.crawler_repository.data.data_source.CrawlerRepositoryDao
import com.rulhouse.evgawatcher.crawler.crawler_repository.domain.repository.CrawlerRepositoryRepository
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class CrawlerRepositoryImpl (
    private val dao: CrawlerRepositoryDao
) : CrawlerRepositoryRepository {
    override suspend fun getProductsFlow(): Flow<List<GpuProduct>> {
        return dao.getProductsFlow()
    }

    override suspend fun insertProducts(products: List<GpuProduct>) {
        dao.insertProducts(products)
    }
}