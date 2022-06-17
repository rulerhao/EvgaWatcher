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

    val myCoroutineScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job()
    }

    val test = MutableStateFlow<List<GpuProduct>?>(null)
    val test2 = MutableStateFlow<List<GpuProduct>?>(null)
    val favoriteProductsFlow = flow {
        getFavoriteProducts()
        emit(favoriteProducts.value)
    }

    val crawlerProductsFlow = flow {
        getFavoriteProducts()
        emit(favoriteProducts.value)
    }

    init {
        test.combine(test2) { favoriteProducts, crawlerProducts ->

        }.launchIn(myCoroutineScope)
        myCoroutineScope.launch {
            Flow
            favoriteProductsFlow.merge
            getFavoriteProducts()
            Log.d("TestJob", "FavoriteProducts = ${favoriteProducts.value}")
        }
        myCoroutineScope.launch {
            getCrawlerGpuProducts()
            Log.d("TestJob", "crawlerGpuProducts = ${crawlerGpuProducts.value}")
        }
        Log.d("TestJob", "play outSide")
    }

    private suspend fun getFavoriteProducts() = coroutineScope {
        _favoriteProducts.value = favoriteGpuProductUseCases.getFavoriteGpuProducts()
    }

    private suspend fun getCrawlerGpuProducts() = coroutineScope {
        _crawlerGpuProducts.value = crawlerUseCases.getGpuItems()
    }
}