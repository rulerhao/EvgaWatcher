package com.rulhouse.evgawatcher.crawler.crawler_repository.domain.use_cases

import com.rulhouse.evgawatcher.crawler.crawler_repository.domain.repository.CrawlerRepositoryRepository
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.InvalidFavoriteGpuProductException
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository

class InsertCrawlerRepository (
    private val repository: CrawlerRepositoryRepository
) {
    suspend operator fun invoke(products: List<GpuProduct>) {
        repository.insertProducts(products)
    }
}