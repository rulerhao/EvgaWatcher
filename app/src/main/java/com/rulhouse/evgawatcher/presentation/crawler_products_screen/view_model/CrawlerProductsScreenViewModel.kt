package com.rulhouse.evgawatcher.presentation.crawler_products_screen.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.methods.crawler.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.use_cases.CrawlerRepositoryUseCase
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.event.CrawlerProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.products_screen.model.ProductState
import com.rulhouse.evgawatcher.presentation.products_screen.view_model.ProductsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrawlerProductsScreenViewModel @Inject constructor(
    private val crawlerUseCases: CrawlerUseCases,
    favoriteGpuProductUseCases: FavoriteGpuProductUseCases,
    userPreferencesDataStoreUseCases: UserPreferencesDataStoreUseCases,
    private val crawlerRepositoryUseCase: CrawlerRepositoryUseCase
) : ProductsScreenViewModel(
    favoriteGpuProductUseCases,
    userPreferencesDataStoreUseCases
) {

    private val _productsState: MutableState<ProductState> =
        mutableStateOf(ProductState.FinishedYet)
    val productsState: State<ProductState> = _productsState

    private val _refreshingCrawler: MutableState<Boolean> =
        mutableStateOf(false)
    val refreshingCrawler: State<Boolean> = _refreshingCrawler

    private var netWorkError = false
    private var crawlerSuccess = false
    private var loadRepositorySuccess = false

    private var crawlerProduct: List<GpuProduct>? = emptyList()

    init {
        viewModelScope.launch {
            setByCrawler()
        }
        viewModelScope.launch {
            crawlerRepositoryUseCase.getCrawlerRepositoryFlow().collect {
                if (!getProductsBeenSet(crawlerProduct)) {
                    if (it.isNotEmpty()) {
                        loadRepositorySuccess = true
                    }
                    setProducts(it)
                    setState()
                }
            }
        }
    }

    fun onEvent(event: CrawlerProductsScreenEvent) {
        when (event) {
            CrawlerProductsScreenEvent.OnRefresh -> {
                viewModelScope.launch {
                    _refreshingCrawler.value = true
                    Log.d("CrawlerError", "refreshCrawler = ${refreshingCrawler.value}")
                    setByCrawler()
                    _refreshingCrawler.value = false
                    Log.d("CrawlerError", "refreshCrawler = ${refreshingCrawler.value}")
                }
            }
        }
    }

    private fun setState() {
        _productsState.value = getState(
            netWorkError = netWorkError,
            crawlerSuccess = crawlerSuccess,
            loadRepositorySuccess = loadRepositorySuccess
        )
    }

    private fun getState(
        crawlerSuccess: Boolean,
        loadRepositorySuccess: Boolean,
        netWorkError: Boolean
    ): ProductState {
        if (loadRepositorySuccess)
            return ProductState.Success
        if (netWorkError)
            return ProductState.NetWorkError
        if (crawlerSuccess)
            return ProductState.Success
        return ProductState.FinishedYet
    }

    private suspend fun setByCrawler() {
        try {
            crawlerProduct = crawlerUseCases.getGpuItems()
            netWorkError = false
            crawlerSuccess = true
            setProducts(crawlerProduct)
            setRepository(crawlerProduct, crawlerRepositoryUseCase)
        } catch (e: Exception) {
            netWorkError = true
            crawlerSuccess = false
            e.printStackTrace()
        }
        setState()
    }

    private fun getProductsBeenSet(products: List<GpuProduct>?): Boolean {
        if (products == null) return false
        else if (products.isEmpty()) return false
        return true
    }

    private suspend fun setRepository(
        products: List<GpuProduct>?,
        crawlerRepositoryUseCase: CrawlerRepositoryUseCase
    ) {
        products?.let {
            products.forEach { product ->
                val repositoryProduct =
                    crawlerRepositoryUseCase.getCrawlerRepositoryProductByName(product.name)
                if (repositoryProduct != null) {
                    crawlerRepositoryUseCase.insertCrawlerRepositoryProduct(product.copy(id = repositoryProduct.id))
                } else {
                    crawlerRepositoryUseCase.insertCrawlerRepositoryProduct(product)
                }
            }
        }
    }
}