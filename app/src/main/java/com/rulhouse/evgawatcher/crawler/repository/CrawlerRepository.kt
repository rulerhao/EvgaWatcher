package com.rulhouse.evgawatcher.crawler.repository

import com.rulhouse.evgawatcher.GpuProduct
import kotlinx.coroutines.flow.Flow

interface CrawlerRepository {
    suspend fun getGpuItems(): List<GpuProduct>?
}