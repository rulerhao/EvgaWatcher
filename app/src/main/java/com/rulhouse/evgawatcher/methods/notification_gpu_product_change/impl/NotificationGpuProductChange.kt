package com.rulhouse.evgawatcher.methods.notification_gpu_product_change.impl

import android.util.Log
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.crawler.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class NotificationGpuProductChange(
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

    fun getProductsDifferenceWithReason(): List<ProductsDifferenceWithReason> {
        getProducts()
        return GetGpuProductsDifference().getDifferenceWithReason(
            favoriteGpuProducts,
            crawlerGpuProducts
        )
    }

    fun getProductsDifferenceWithReasonFlow(): Flow<List<ProductsDifferenceWithReason>> {
        val crawlerProductsFlow = flow {
            getCrawlerGpuProducts()
            emit(crawlerGpuProducts)
        }

        return combine(
            favoriteGpuProductUseCases.getFavoriteGpuProductsFlow(),
            crawlerProductsFlow
        ) { favoriteProducts, crawlerProducts ->
            favoriteGpuProducts = favoriteProducts
            crawlerGpuProducts = crawlerProducts
            GetGpuProductsDifference().getDifferenceWithReason(
                favoriteProducts,
                crawlerProducts
            )
        }
    }

    private fun getProducts() {
        runBlocking {
            withContext(Dispatchers.Default) {
                combine(
                    favoriteProductsFlow,
                    crawlerProductsFlow
                ) { favoriteProducts, crawlerProducts ->
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