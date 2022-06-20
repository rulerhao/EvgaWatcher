package com.rulhouse.evgawatcher.notification_gpu_product_change.impl

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NotificationGpuProductChange @Inject constructor(
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
        return getDifference()
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

    private fun getDifference(): List<ProductsDifference> {
        val tempDifferenceGpuProducts = emptyList<ProductsDifference>().toMutableList()
        favoriteGpuProducts?.forEach { favoriteProduct ->
            crawlerGpuProducts?.forEach { crawlerProduct ->
                if (favoriteProduct.name == crawlerProduct.name) {
                    val buyableDifference = buyableDifference(favoriteProduct, crawlerProduct)
                    if (buyableDifference != null) {
                        tempDifferenceGpuProducts.add(buyableDifference)
                    }
                    val priceDifference = priceDifference(favoriteProduct, crawlerProduct)
                    if (priceDifference != null) {
                        tempDifferenceGpuProducts.add(priceDifference)
                    }
                }
            }
        }
        return tempDifferenceGpuProducts
    }

    private fun buyableDifference(favoriteProduct: GpuProduct, crawlerProduct: GpuProduct): ProductsDifference? {
        if (favoriteProduct.canBeBought != crawlerProduct.canBeBought) {
            if (crawlerProduct.canBeBought == true)
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.GetBuyable)
            else if (crawlerProduct.canBeBought == false)
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.GetNotBuyable)
        }
        return null
    }
    
    private fun priceDifference(favoriteProduct: GpuProduct, crawlerProduct: GpuProduct): ProductsDifference? {
        if (favoriteProduct.price != crawlerProduct.price) {
            if (favoriteProduct.price!! > crawlerProduct.price!!)
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.PriceGetLower)
            else 
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.PriceGetHigher)
        }
        return null
    }
    
    private suspend fun getFavoriteProducts() = coroutineScope {
        favoriteGpuProducts = favoriteGpuProductUseCases.getFavoriteGpuProducts()
    }

    private suspend fun getCrawlerGpuProducts() = coroutineScope {
        crawlerGpuProducts = crawlerUseCases.getGpuItems()
    }
}