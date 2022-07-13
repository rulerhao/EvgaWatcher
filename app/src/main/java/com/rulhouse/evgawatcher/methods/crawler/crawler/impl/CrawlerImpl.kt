package com.rulhouse.evgawatcher.methods.crawler.crawler.impl

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.crawler.crawler.util.WebCrawler
import com.rulhouse.evgawatcher.methods.crawler.crawler.repository.CrawlerRepository

class CrawlerImpl (
) : CrawlerRepository {

    override suspend fun getGpuItems(): List<GpuProduct>? {
        return WebCrawler.getCrawlerProducts()
    }
}