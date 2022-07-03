package com.rulhouse.evgawatcher.presentation.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesState
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.presentation.products_screen.ExpandCollapseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val crawlerUseCases: CrawlerUseCases,
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases,
    private val userPreferencesDataStoreUseCases: UserPreferencesDataStoreUseCases,
) : ViewModel() {

    private val _userPreferencesState: MutableState<UserPreferencesState> = mutableStateOf(UserPreferencesState())
    val userPreferencesState: State<UserPreferencesState> = _userPreferencesState

    private val _crawlerProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val crawlerProducts: State<List<GpuProduct>?> = _crawlerProducts

    private val _showingProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val showingProducts: State<List<GpuProduct>?> = _showingProducts

    private val _showingGpuProductsSortedBySerial: MutableState<List<List<GpuProduct>>?> = mutableStateOf(
        emptyList()
    )
    val showingGpuProductsSortedBySerial: State<List<List<GpuProduct>>?> = _showingGpuProductsSortedBySerial

    private val _productsSortedBySerialModel: MutableState<List<ExpandCollapseModel>?> =
        mutableStateOf(
            emptyList()
        )
    val productsSortedBySerialModel: State<List<ExpandCollapseModel>?> =
        _productsSortedBySerialModel

    private val _favoriteProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val favoriteProducts: State<List<GpuProduct>?> = _favoriteProducts

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnCollapseColumnStateChanged -> {
                onCollapseColumnStateChanged(event.index)
            }
            is MainScreenEvent.OnShowingOutOfStockChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updateShowingOutOfStock(!userPreferencesState.value.showingOutOfStock)
                }
            }
            is MainScreenEvent.OnPriceAscendingChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updatePriceAscending(!userPreferencesState.value.priceAscending)
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            _crawlerProducts.value = crawlerUseCases.getGpuItems()
            setProducts()
        }
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProductsFlow().collect {
                _favoriteProducts.value = it
                setFavoriteProducts()
            }
        }
        viewModelScope.launch {
            userPreferencesDataStoreUseCases.getUserPreferencesDataStoreFlow().collect {
                if (userPreferencesState.value.showingOutOfStock != it.showingOutOfStock) {
                    _userPreferencesState.value = userPreferencesState.value.copy(
                        showingOutOfStock = it.showingOutOfStock,
                    )
                    setProducts()
                }
                if (userPreferencesState.value.priceAscending != it.priceAscending) {
                    _userPreferencesState.value = userPreferencesState.value.copy(
                        priceAscending = it.priceAscending
                    )
                    setFavoriteProducts()
                }
            }
        }
    }

    private fun setProducts() {
        crawlerProducts.value?.let {
            var tempProducts: List<GpuProduct> = emptyList()
            tempProducts = GpuProductsMethods.getProductsWithFavorites(
                products = crawlerProducts.value,
                favoriteProducts = favoriteProducts.value
            )
            _showingProducts.value = GpuProductsMethods.sortProducts(
                products = tempProducts,
                showingOutOfStock = userPreferencesState.value.showingOutOfStock,
                priceAscending = userPreferencesState.value.priceAscending
            )
            _showingGpuProductsSortedBySerial.value = GpuProductsMethods.getNamesBySerial(showingProducts.value)
            _productsSortedBySerialModel.value = GpuProductsMethods.getCollapsedModels(showingGpuProductsSortedBySerial.value)
        }
    }

    private fun setFavoriteProducts() {
        crawlerProducts.value?.let {
            var tempProducts: List<GpuProduct> = emptyList()
            tempProducts = GpuProductsMethods.getProductsWithFavorites(
                products = crawlerProducts.value,
                favoriteProducts = favoriteProducts.value
            )
            _showingProducts.value = GpuProductsMethods.sortProducts(
                products = tempProducts,
                showingOutOfStock = userPreferencesState.value.showingOutOfStock,
                priceAscending = userPreferencesState.value.priceAscending
            )
            _showingGpuProductsSortedBySerial.value =
                GpuProductsMethods.getNamesBySerial(showingProducts.value)
        }
    }

    private fun onCollapseColumnStateChanged(index: Int) {
        val newModel = productsSortedBySerialModel.value?.toMutableList()
        newModel!![index] = newModel[index].copy(
            isOpen = !newModel[index].isOpen
        )
        _productsSortedBySerialModel.value = newModel
    }
}