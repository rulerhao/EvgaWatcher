package com.rulhouse.evgawatcher.crawler.impl

import com.rulhouse.evgawatcher.GpuProduct
import com.rulhouse.evgawatcher.WebCrawler
import com.rulhouse.evgawatcher.crawler.repository.CrawlerRepository
import kotlinx.coroutines.flow.Flow

class CrawlerImpl (
) : CrawlerRepository {

    override suspend fun getGpuItems(): List<GpuProduct> {
        return WebCrawler.test()
    }
}