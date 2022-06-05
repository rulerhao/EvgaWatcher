package com.rulhouse.evgawatcher.crawler.feature_node.domain.repository

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import kotlinx.coroutines.flow.Flow

interface FavoriteGpuProductRepository {
    fun getFavoriteGpuProducts(): Flow<List<GpuProduct>>

    suspend fun getFavoriteGpuProductsByName(name: String): GpuProduct?

    suspend fun getFavoriteGpuProductById(id: Int): GpuProduct?

    suspend fun insertFavoriteGpuProduct(measurement: GpuProduct)

    suspend fun deleteFavoriteGpuProduct(measurement: GpuProduct)
}