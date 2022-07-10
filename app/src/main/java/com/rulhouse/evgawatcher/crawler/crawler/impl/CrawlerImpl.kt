package com.rulhouse.evgawatcher.crawler.crawler.impl

import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.crawler.util.WebCrawler
import com.rulhouse.evgawatcher.crawler.crawler.repository.CrawlerRepository

class CrawlerImpl (
) : CrawlerRepository {

    override suspend fun getGpuItems(): List<GpuProduct>? {
        return WebCrawler.getCrawlerProducts()
    }
}