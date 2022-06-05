package com.rulhouse.evgawatcher.crawler.use_cases

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.repository.CrawlerRepository

class GetGpuItems(
    private val repository: CrawlerRepository
) {
    suspend operator fun invoke(): List<GpuProduct>? {
        return repository.getGpuItems()
    }
}