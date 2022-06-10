package com.rulhouse.evgawatcher.presentation.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.data.InvalidFavoriteGpuProductException
import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.presentation.products_screen.ExpandCollapseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    crawlerUseCases: CrawlerUseCases,
    favoriteGpuProductUseCases: FavoriteGpuProductUseCases
) : ViewModel() {
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
                val newModel = productsSortedBySerialModel.value?.toMutableList()
                newModel!![event.index] = newModel[event.index].copy(
                    isOpen = !newModel[event.index].isOpen
                )
                _productsSortedBySerialModel.value = newModel
            }
        }
    }

    init {
        viewModelScope.launch {
            _products.value = crawlerUseCases.getGpuItems()
            _productsSortedBySerial.value = GpuProductsMethods.getNamesBySerial(products.value)
            val models: MutableList<ExpandCollapseModel> = mutableListOf()
            productsSortedBySerial.value?.forEachIndexed { index, item ->
                models.add(
                    ExpandCollapseModel(
                        title = GpuProductsMethods.getNameBySerial(item[0].name)!!,
                        isOpen = false
                    )
                )
            }
            _productsSortedBySerialModel.value = models
        }
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProducts().collectLatest {
                Log.d("FavoriteChange", "Favorite change")
                _favoriteProducts.value = it
                val newProducts = mutableListOf<GpuProduct>()
                products.value?.forEach { product ->
                    var newProduct = product
                    favoriteProducts.value?.forEach { favoriteProduct ->
                        if (favoriteProduct.name == product.name) {
                            Log.d("FavoriteChange", "Favorite change -- ${product.serial} = ${favoriteProduct.favorite}")
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
        }
    }
}