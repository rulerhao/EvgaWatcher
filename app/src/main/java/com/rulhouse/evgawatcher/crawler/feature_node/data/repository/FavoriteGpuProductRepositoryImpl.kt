package com.rulhouse.ruler.feature_node.data.repository

import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.data.data_source.FavoriteGpuProductsDao
import com.rulhouse.evgawatcher.crawler.feature_node.domain.repository.FavoriteGpuProductRepository
import kotlinx.coroutines.flow.Flow

class FavoriteGpuProductRepositoryImpl (
    private val dao: FavoriteGpuProductsDao
) : FavoriteGpuProductRepository {

    override fun getFavoriteGpuProducts(): Flow<List<GpuProduct>> {
        return dao.getFavoriteGpuProducts()
    }

    override suspend fun getFavoriteGpuProductsByName(name: String): GpuProduct? {
        return dao.getFavoriteGpuProductsByName(name)
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