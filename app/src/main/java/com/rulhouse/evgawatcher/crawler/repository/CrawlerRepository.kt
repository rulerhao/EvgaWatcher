package com.rulhouse.evgawatcher.crawler.repository

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct

interface CrawlerRepository {
    suspend fun getGpuItems(): List<GpuProduct>?
}