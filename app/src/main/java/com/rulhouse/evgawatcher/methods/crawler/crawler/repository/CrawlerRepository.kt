package com.rulhouse.evgawatcher.methods.crawler.crawler.repository

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct

interface CrawlerRepository {
    suspend fun getGpuItems(): List<GpuProduct>?
}