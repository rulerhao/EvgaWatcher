package com.rulhouse.evgawatcher.crawler.impl

import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.WebCrawler
import com.rulhouse.evgawatcher.crawler.repository.CrawlerRepository

class CrawlerImpl (
) : CrawlerRepository {

    override suspend fun getGpuItems(): List<GpuProduct>? {
        return WebCrawler.test()
    }
}