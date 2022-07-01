package com.rulhouse.evgawatcher.presentation.screen

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

    private val _products: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val products: State<List<GpuProduct>?> = _products

    private val _productsSortedBySerial: MutableState<List<List<GpuProduct>>?> = mutableStateOf(
        emptyList()
    )
    val productsSortedBySerial: State<List<List<GpuProduct>>?> = _productsSortedBySerial

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
            _products.value = crawlerUseCases.getGpuItems()
            _productsSortedBySerial.value = GpuProductsMethods.getNamesBySerial(products.value)
            setCollapsedModels(productsSortedBySerial.value)
            setFavoriteValue(favoriteProducts = favoriteProducts.value)
        }
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProductsFlow().collectLatest {
                _favoriteProducts.value = it
                setFavoriteValue(it)
            }
        }
        viewModelScope.launch {
            userPreferencesDataStoreUseCases.getUserPreferencesDataStoreFlow().collect {
                _userPreferencesState.value = userPreferencesState.value.copy(
                    showingOutOfStock = it.showingOutOfStock,
                    priceAscending = it.priceAscending
                )
            }
        }
    }

    private fun setCollapsedModels(productsSortedBySerial: List<List<GpuProduct>>?) {
        if (productsSortedBySerial == null) return
        val models: MutableList<ExpandCollapseModel> = mutableListOf()
        productsSortedBySerial.forEach { item ->
            models.add(
                ExpandCollapseModel(
                    title = GpuProductsMethods.getNameBySerial(item[0].name)!!,
                    isOpen = false
                )
            )
        }
        _productsSortedBySerialModel.value = models
    }

    private fun setFavoriteValue(favoriteProducts: List<GpuProduct>?) {
        if (favoriteProducts == null) return
        val newProducts = mutableListOf<GpuProduct>()
        products.value?.forEach { product ->
            var newProduct = product.copy(favorite = false)
            favoriteProducts.forEach { favoriteProduct ->
                if (favoriteProduct.name == product.name) {
                    newProduct = newProduct.copy(
                        favorite = favoriteProduct.favorite
                    )
                }
            }
            newProducts.add(newProduct)
        }
        _products.value = newProducts
        _productsSortedBySerial.value = GpuProductsMethods.getNamesBySerial(products.value)
    }

    private fun onCollapseColumnStateChanged(index: Int) {
        val newModel = productsSortedBySerialModel.value?.toMutableList()
        newModel!![index] = newModel[index].copy(
            isOpen = !newModel[index].isOpen
        )
        _productsSortedBySerialModel.value = newModel
    }

    private fun changeFilter() {

    }
}