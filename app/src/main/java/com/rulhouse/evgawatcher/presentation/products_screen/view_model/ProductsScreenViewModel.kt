package com.rulhouse.evgawatcher.presentation.products_screen.view_model

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
import com.rulhouse.evgawatcher.presentation.products_screen.model.ExpandCollapseModel
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ProductsScreenViewModel @Inject constructor(
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases,
    private val userPreferencesDataStoreUseCases: UserPreferencesDataStoreUseCases,
) : ViewModel() {

    private var products: List<GpuProduct>? = emptyList()

    private var favoriteProducts: List<GpuProduct>? = emptyList()

    private val _userPreferencesState: MutableState<UserPreferencesState> =
        mutableStateOf(UserPreferencesState())
    val userPreferencesState: State<UserPreferencesState> = _userPreferencesState

    private val _showingProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    private val showingProducts: State<List<GpuProduct>?> = _showingProducts

    private var allProductsSerialList: List<List<GpuProduct>>? = emptyList()
    private val _showingGpuProductsSortedBySerial: MutableState<List<List<GpuProduct>>?> =
        mutableStateOf(emptyList())
    val showingGpuProductsSortedBySerial: State<List<List<GpuProduct>>?> =
        _showingGpuProductsSortedBySerial

    private var allProductsModels: List<ExpandCollapseModel>? =
        emptyList<ExpandCollapseModel>().toMutableList()
    private val _productsSortedBySerialModel: MutableState<List<ExpandCollapseModel>?> =
        mutableStateOf(emptyList())
    val productsSortedBySerialModel: State<List<ExpandCollapseModel>?> =
        _productsSortedBySerialModel

    open fun onEvent(event: ProductsScreenEvent) {
        when (event) {
            is ProductsScreenEvent.OnCollapseColumnStateChanged -> {
                onCollapseColumnStateChanged(event.index)
            }
            is ProductsScreenEvent.OnShowingOutOfStockChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updateShowingOutOfStock(!userPreferencesState.value.showingOutOfStock)
                }
            }
            is ProductsScreenEvent.OnPriceAscendingChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updatePriceAscending(!userPreferencesState.value.priceAscending)
                }
            }
            is ProductsScreenEvent.OnShowingNoPriceChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updateShowingNoPriceProduct(!userPreferencesState.value.showingNoPrice)
                }
            }
        }
    }

    init {
        // Get Favorite Products
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProductsFlow().collect {
                favoriteProducts = it
                setAllProducts(products)
            }
        }
        viewModelScope.launch {
            userPreferencesDataStoreUseCases.getUserPreferencesDataStoreFlow().collect {
                if (userPreferencesState.value.showingOutOfStock != it.showingOutOfStock) {
                    _userPreferencesState.value = userPreferencesState.value.copy(
                        showingOutOfStock = it.showingOutOfStock,
                    )
                    setAllProducts(products)
                }
                if (userPreferencesState.value.priceAscending != it.priceAscending) {
                    _userPreferencesState.value = userPreferencesState.value.copy(
                        priceAscending = it.priceAscending
                    )
                    setAllProducts(products)
                }
                if (userPreferencesState.value.showingNoPrice != it.showingNoPrice) {
                    _userPreferencesState.value = userPreferencesState.value.copy(
                        showingNoPrice = it.showingNoPrice
                    )
                    setAllProducts(products)
                }
            }
        }
    }

    protected fun setProducts(products: List<GpuProduct>?) {
        this.products = products
        allProductsSerialList = GpuProductsMethods.getNamesBySerial(this.products)
        allProductsModels = GpuProductsMethods.getCollapsedModels(allProductsSerialList)
        setAllProducts(products)
    }

    private fun setAllProducts(products: List<GpuProduct>?) {
        products?.let {
            val tempProducts: List<GpuProduct> = GpuProductsMethods.getProductsWithFavorites(
                products = it,
                favoriteProducts = favoriteProducts
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
                GpuProductsMethods.getModels(showingGpuProductsSortedBySerial.value, allProductsModels)
        }
    }

    private fun onCollapseColumnStateChanged(index: Int) {
        _productsSortedBySerialModel.value =
            GpuProductsMethods.getToggledSortedModels(index, productsSortedBySerialModel.value)
        allProductsModels = GpuProductsMethods.getToggledAllModels(
            index = index,
            allModels = allProductsModels,
            sortedModels = productsSortedBySerialModel.value
        )
    }
}