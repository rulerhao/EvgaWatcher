package com.rulhouse.evgawatcher.methods.crawler.crawler.use_cases

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.crawler.crawler.repository.CrawlerRepository

class GetGpuItems(
    private val repository: CrawlerRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(): List<GpuProduct>? {
        return repository.getGpuItems()
    }
}