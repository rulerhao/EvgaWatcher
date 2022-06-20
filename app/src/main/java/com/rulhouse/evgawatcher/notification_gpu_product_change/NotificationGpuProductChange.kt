package com.rulhouse.evgawatcher.notification_gpu_product_change

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NotificationGpuProductChange @Inject constructor(
    private val crawlerUseCases: CrawlerUseCases,
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases
) {

    private val _favoriteProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val favoriteProducts: State<List<GpuProduct>?> = _favoriteProducts

    private val _crawlerGpuProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val crawlerGpuProducts: State<List<GpuProduct>?> = _crawlerGpuProducts

    private lateinit var  differenceGpuProducts: List<ProductsDifference?>

    val myCoroutineScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job()
    }

    val favoriteProductsFlow = flow {
        getFavoriteProducts()
        emit(favoriteProducts.value)
    }

    val crawlerProductsFlow = flow {
        getCrawlerGpuProducts()
        emit(crawlerGpuProducts.value)
    }

    fun getDifferenceProducts(): List<ProductsDifference> {
        getProducts()
        return getDifference()
    }

    private fun getProducts() {
        runBlocking {
            withContext(Dispatchers.Default) {
                combine(favoriteProductsFlow, crawlerProductsFlow) { favoriteProducts, crawlerProducts ->
                    _favoriteProducts.value = favoriteProducts
                    _crawlerGpuProducts.value = crawlerProducts
                }.launchIn(this)
            }
        }
    }

    private fun getDifference(): List<ProductsDifference> {
        val tempDifferenceGpuProducts = emptyList<ProductsDifference>().toMutableList()
        favoriteProducts.value?.forEachIndexed { fIdx, favoriteProduct ->
            crawlerGpuProducts.value?.forEachIndexed { CIdx, crawlerProduct ->
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
        _favoriteProducts.value = favoriteGpuProductUseCases.getFavoriteGpuProducts()
    }

    private suspend fun getCrawlerGpuProducts() = coroutineScope {
        _crawlerGpuProducts.value = crawlerUseCases.getGpuItems()
    }
}