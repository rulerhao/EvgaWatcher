package com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.repository

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import kotlinx.coroutines.flow.Flow

interface CrawlerRepositoryRepository {
    suspend fun getProductsFlow(): Flow<List<GpuProduct>>

    suspend fun insertProducts(products: List<GpuProduct>)
}