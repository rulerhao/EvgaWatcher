package com.rulhouse.evgawatcher.methods.favorite_products.impl

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.data.data_source.FavoriteGpuProductsDao
import com.rulhouse.evgawatcher.methods.favorite_products.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class FavoriteGpuProductImpl (
    private val dao: FavoriteGpuProductsDao
) : FavoriteGpuProductRepository {

    override fun getFavoriteGpuProductsFlow(): Flow<List<GpuProduct>> {
        return dao.getFavoriteGpuProductsFlow()
    }

    override suspend fun getFavoriteGpuProducts(): List<GpuProduct> {
        return dao.getFavoriteGpuProducts()
    }

    override fun getFavoriteGpuProductFlowByName(name: String): Flow<GpuProduct> {
        return dao.getFavoriteGpuProductFlowByName(name)
    }

    override suspend fun getFavoriteGpuProductByName(name: String): GpuProduct? {
        return dao.getFavoriteGpuProductByName(name)
    }

    override suspend fun getFavoriteGpuProductById(id: Int): GpuProduct? {
        return dao.getFavoriteGpuProductById(id)
    }

    override suspend fun insertFavoriteGpuProduct(gpuProduct: GpuProduct) {
        dao.insertFavoriteGpuProduct(gpuProduct)
    }

    override suspend fun deleteFavoriteGpuProduct(gpuProduct: GpuProduct) {
        dao.deleteFavoriteGpuProduct(gpuProduct)
    }
}