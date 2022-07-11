package com.rulhouse.evgawatcher.presentation.products_screen.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.methods.crawler.crawler.util.GpuProductsMethods
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.data.UserPreferencesState
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.presentation.products_screen.item.expand_collapse_column.model.ExpandCollapseModel
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

    private var allProductsSerialList: List<List<GpuProduct>>? = emptyList()
    private var allProductsModels: List<ExpandCollapseModel>? =
        emptyList<ExpandCollapseModel>().toMutableList()

    private val _userPreferencesState: MutableState<UserPreferencesState> =
        mutableStateOf(UserPreferencesState())
    val userPreferencesState: State<UserPreferencesState> = _userPreferencesState

    private val _showingGpuProductsSortedBySerial: MutableState<List<List<GpuProduct>>?> =
        mutableStateOf(emptyList())
    val showingGpuProductsSortedBySerial: State<List<List<GpuProduct>>?> =
        _showingGpuProductsSortedBySerial

    private val _productsSortedBySerialModel: MutableState<List<ExpandCollapseModel>?> =
        mutableStateOf(emptyList())
    val productsSortedBySerialModel: State<List<ExpandCollapseModel>?> =
        _productsSortedBySerialModel

    open fun onEvent(event: ProductsScreenEvent) {
        when (event) {
            is ProductsScreenEvent.ToggleFavoriteGpuProduct -> {
                viewModelScope.launch {
                    toggleFavoriteProducts(favoriteGpuProductUseCases, event.gpuProduct)
                }
            }
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
            is ProductsScreenEvent.OnPriceSortedChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updatePriceAscending(event.isOn)
                }
            }
            is ProductsScreenEvent.OnShowingNoPriceChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updateShowingNoPriceProduct(!userPreferencesState.value.showingNoPrice)
                }
            }
            is ProductsScreenEvent.OnFilterStateChanged -> {
                viewModelScope.launch {
                    userPreferencesDataStoreUseCases.updateFilterState(!userPreferencesState.value.filterState)
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
                if (userPreferencesState.value.filterState != it.filterState) {
                    _userPreferencesState.value = userPreferencesState.value.copy(
                        filterState = it.filterState
                    )
                }
            }
        }
    }

    protected fun setProducts(products: List<GpuProduct>?) {
        this.products = products
        allProductsSerialList = GpuProductsMethods.getNamesBySerial(this.products)
//        allProductsModels = GpuProductsMethods.getCollapsedModels(allProductsSerialList)
        allProductsModels = GpuProductsMethods.getModels(allProductsSerialList, allProductsModels)
        setAllProducts(products)
    }

    private fun setAllProducts(products: List<GpuProduct>?) {
        products?.let {
            val tempProducts: List<GpuProduct> = GpuProductsMethods.getProductsWithFavorites(
                products = it,
                favoriteProducts = favoriteProducts
            )
            val showingProducts = GpuProductsMethods.sortProductsWithPrice(
                products = tempProducts,
                showingOutOfStock = userPreferencesState.value.showingOutOfStock,
                priceAscending = userPreferencesState.value.priceAscending,
                showingNoPrice = userPreferencesState.value.showingNoPrice
            )
            _showingGpuProductsSortedBySerial.value =
                GpuProductsMethods.getNamesBySerial(showingProducts)
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

    private suspend fun toggleFavoriteProducts(favoriteGpuProductUseCases: FavoriteGpuProductUseCases, product: GpuProduct) {
        val favoriteGpuProduct = favoriteGpuProductUseCases.getFavoriteGpuProductByName(product.name)
        if (favoriteGpuProduct != null) {
            favoriteGpuProductUseCases.deleteFavoriteGpuProduct(favoriteGpuProduct)
        } else {
            favoriteGpuProductUseCases.addFavoriteGpuProduct(product.copy(favorite = true))
        }
    }
}