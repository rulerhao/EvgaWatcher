package com.rulhouse.evgawatcher.presentation.crawler_products_screen.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.methods.crawler.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.use_cases.CrawlerRepositoryUseCase
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.data.UserPreferencesState
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.presentation.products_screen.view_model.ProductsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrawlerProductsScreenViewModel @Inject constructor(
    crawlerUseCases: CrawlerUseCases,
    favoriteGpuProductUseCases: FavoriteGpuProductUseCases,
    userPreferencesDataStoreUseCases: UserPreferencesDataStoreUseCases,
    crawlerRepositoryUseCase: CrawlerRepositoryUseCase
): ProductsScreenViewModel(
    favoriteGpuProductUseCases,
    userPreferencesDataStoreUseCases
) {

    private val _loadingCrawlerState: MutableState<Boolean> =
        mutableStateOf(true)
    val loadingCrawlerState: State<Boolean> = _loadingCrawlerState

    private val _loadingRepositoryState: MutableState<Boolean> =
        mutableStateOf(true)
    val loadingRepositoryState: State<Boolean> = _loadingRepositoryState

    private var crawlerProduct: List<GpuProduct>? = emptyList()
    init {
        viewModelScope.launch {
            crawlerProduct = crawlerUseCases.getGpuItems()
            _loadingCrawlerState.value = false
            setProducts(crawlerProduct)
            setRepository(crawlerProduct, crawlerRepositoryUseCase)
        }
        viewModelScope.launch {
            crawlerRepositoryUseCase.getCrawlerRepositoryFlow().collect {
                if (!getProductsBeenSet(crawlerProduct)) {
                    if (it.isNotEmpty())
                        _loadingRepositoryState.value = false
                    setProducts(it)
                }
            }
        }
    }

    private fun getProductsBeenSet(products: List<GpuProduct>?): Boolean {
        if (products == null) return false
        else if (products.isEmpty()) return false
        return true
    }

    private suspend fun setRepository(products: List<GpuProduct>?, crawlerRepositoryUseCase: CrawlerRepositoryUseCase) {
        products?.let {
            products.forEach { product ->
                val repositoryProduct = crawlerRepositoryUseCase.getCrawlerRepositoryProductByName(product.name)
                if (repositoryProduct != null) {
                    crawlerRepositoryUseCase.insertCrawlerRepositoryProduct(product.copy(id = repositoryProduct.id))
                } else {
                    crawlerRepositoryUseCase.insertCrawlerRepositoryProduct(product)
                }
            }
        }
    }
}