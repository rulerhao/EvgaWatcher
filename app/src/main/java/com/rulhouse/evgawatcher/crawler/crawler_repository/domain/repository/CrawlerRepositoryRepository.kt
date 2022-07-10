package com.rulhouse.evgawatcher.crawler.crawler_repository.domain.repository

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import kotlinx.coroutines.flow.Flow

interface CrawlerRepositoryRepository {
    suspend fun getProductsFlow(): Flow<List<GpuProduct>>

    suspend fun insertProducts(products: List<GpuProduct>)
}