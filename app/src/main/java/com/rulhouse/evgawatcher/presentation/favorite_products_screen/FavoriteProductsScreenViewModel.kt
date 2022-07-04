package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesState
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.presentation.products_screen.ExpandCollapseModel
import com.rulhouse.evgawatcher.presentation.products_screen.boolean_filter_chip.BooleanFilterChipEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsScreenViewModel @Inject constructor(
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases,
    private val userPreferencesDataStoreUseCases: UserPreferencesDataStoreUseCases,
) : ViewModel() {

    private val _userPreferencesState: MutableState<UserPreferencesState> =
        mutableStateOf(UserPreferencesState())
    val userPreferencesState: State<UserPreferencesState> = _userPreferencesState

    private val _products: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    private val products: State<List<GpuProduct>?> = _products

    private val _showingProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    private val showingProducts: State<List<GpuProduct>?> = _showingProducts

    private val _showingGpuProductsSortedBySerial: MutableState<List<List<GpuProduct>>?> =
        mutableStateOf(emptyList())
    val showingGpuProductsSortedBySerial: State<List<List<GpuProduct>>?> =
        _showingGpuProductsSortedBySerial

    private val _productsSortedBySerialModel: MutableState<List<ExpandCollapseModel>?> =
        mutableStateOf(emptyList())
    val productsSortedBySerialModel: State<List<ExpandCollapseModel>?> =
        _productsSortedBySerialModel

    private val _favoriteProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val favoriteProducts: State<List<GpuProduct>?> = _favoriteProducts

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProductsFlow().collectLatest {
                _products.value = it
                setProducts()
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
                if (userPreferencesState.value.showingNoPrice != it.showingNoPrice) {
                    _userPreferencesState.value = userPreferencesState.value.copy(
                        showingNoPrice = it.showingNoPrice
                    )
                    setFavoriteProducts()
                }
            }
        }
    }

    fun onEvent(event: FavoriteProductsScreenEvent) {
        when (event) {
            is FavoriteProductsScreenEvent.OnCollapseColumnStateChanged -> {
                onCollapseColumnStateChanged(event.index)
            }
            is FavoriteProductsScreenEvent.ToggleFavoriteGpuProduct -> {
                if (products.value != null) {
                    products.value!!.forEach { product ->
                        if (product.name == event.gpuProduct.name) {
                            viewModelScope.launch {
                                favoriteGpuProductUseCases.deleteFavoriteGpuProduct(product)
                            }
                            return
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: BooleanFilterChipEvent) {
        when (event) {
            is BooleanFilterChipEvent.OnShowingOutOfStockChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updateShowingOutOfStock(!userPreferencesState.value.showingOutOfStock)
                }
            }
            is BooleanFilterChipEvent.OnPriceAscendingChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updatePriceAscending(!userPreferencesState.value.priceAscending)
                }
            }
            is BooleanFilterChipEvent.OnShowingNoPriceChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updateShowingNoPriceProduct(!userPreferencesState.value.showingNoPrice)
                }
            }
        }
    }

    private fun setProducts() {
        products.value?.let {
            val tempProducts: List<GpuProduct> = GpuProductsMethods.getProductsWithFavorites(
                products = it,
                favoriteProducts = favoriteProducts.value
            )
            _showingProducts.value = GpuProductsMethods.sortProductsWithPrice(
                products = tempProducts,
                showingOutOfStock = userPreferencesState.value.showingOutOfStock,
                priceAscending = userPreferencesState.value.priceAscending,
                showingNoPrice = userPreferencesState.value.showingNoPrice
            )
            _showingGpuProductsSortedBySerial.value =
                GpuProductsMethods.getNamesBySerial(showingProducts.value)
            _productsSortedBySerialModel.value =
                GpuProductsMethods.getCollapsedModels(showingGpuProductsSortedBySerial.value)
        }
    }

    private fun setFavoriteProducts() {
        products.value?.let {
            val tempProducts: List<GpuProduct> = GpuProductsMethods.getProductsWithFavorites(
                products = it,
                favoriteProducts = favoriteProducts.value
            )
            _showingProducts.value = GpuProductsMethods.sortProductsWithPrice(
                products = tempProducts,
                showingOutOfStock = userPreferencesState.value.showingOutOfStock,
                priceAscending = userPreferencesState.value.priceAscending,
                showingNoPrice = userPreferencesState.value.showingNoPrice
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

    sealed class UiEvent {
        data class OnCollapseColumnStateChanged(val index: Int) : UiEvent()
    }
}