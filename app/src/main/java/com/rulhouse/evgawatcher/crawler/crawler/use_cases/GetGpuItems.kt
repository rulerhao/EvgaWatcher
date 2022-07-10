package com.rulhouse.evgawatcher.crawler.crawler.use_cases

import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.crawler.repository.CrawlerRepository

class GetGpuItems(
    private val repository: CrawlerRepository
) {
    suspend operator fun invoke(): List<GpuProduct>? {
        return repository.getGpuItems()
    }
}