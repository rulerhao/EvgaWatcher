package com.rulhouse.evgawatcher.notification_gpu_product_change.impl

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NotificationGpuProductChange (
    private val crawlerUseCases: CrawlerUseCases,
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases
) {

    private var favoriteGpuProducts: List<GpuProduct>? = emptyList()

    private var crawlerGpuProducts: List<GpuProduct>? = emptyList()

    private val favoriteProductsFlow = flow {
        getFavoriteProducts()
        emit(favoriteGpuProducts)
    }

    private val crawlerProductsFlow = flow {
        getCrawlerGpuProducts()
        emit(crawlerGpuProducts)
    }

    fun getDifferenceProducts(): List<ProductsDifference> {
        getProducts()
        return GetGpuProductsDifference().getDifference(favoriteGpuProducts, crawlerGpuProducts)
    }

    private fun getProducts() {
        runBlocking {
            withContext(Dispatchers.Default) {
                combine(favoriteProductsFlow, crawlerProductsFlow) { favoriteProducts, crawlerProducts ->
                    favoriteGpuProducts = favoriteProducts
                    crawlerGpuProducts = crawlerProducts
                }.launchIn(this)
            }
        }
    }

    private suspend fun getFavoriteProducts() = coroutineScope {
        favoriteGpuProducts = favoriteGpuProductUseCases.getFavoriteGpuProducts()
    }

    private suspend fun getCrawlerGpuProducts() = coroutineScope {
        crawlerGpuProducts = crawlerUseCases.getGpuItems()
    }
}