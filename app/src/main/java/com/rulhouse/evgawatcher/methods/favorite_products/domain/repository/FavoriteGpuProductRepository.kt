package com.rulhouse.evgawatcher.methods.favorite_products.domain.repository

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import kotlinx.coroutines.flow.Flow

interface FavoriteGpuProductRepository {
    fun getFavoriteGpuProductsFlow(): Flow<List<GpuProduct>>

    suspend fun getFavoriteGpuProducts(): List<GpuProduct>

    fun getFavoriteGpuProductFlowByName(name: String): Flow<GpuProduct>

    suspend fun getFavoriteGpuProductByName(name: String): GpuProduct?

    suspend fun getFavoriteGpuProductById(id: Int): GpuProduct?

    suspend fun insertFavoriteGpuProduct(measurement: GpuProduct)

    suspend fun deleteFavoriteGpuProduct(measurement: GpuProduct)
}