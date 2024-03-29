package com.rulhouse.evgawatcher.methods.crawler.crawler_repository.impl

import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.data.data_source.CrawlerRepositoryDao
import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.repository.CrawlerRepositoryRepository
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
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

    override suspend fun insertProduct(product: GpuProduct) {
        dao.insertProduct(product)
    }

    override suspend fun getProductByName(name: String): GpuProduct? {
        return dao.getProductByName(name)
    }
}