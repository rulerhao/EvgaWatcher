package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.presentation.product_screen.ProductScreenViewModel
import com.rulhouse.evgawatcher.presentation.products_screen.ExpandCollapseModel
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsScreenViewModel @Inject constructor(
    favoriteGpuProductUseCases: FavoriteGpuProductUseCases
): ViewModel() {
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProducts().collectLatest {
                _products.value = it
                _productsSortedBySerial.value = GpuProductsMethods.getNamesBySerial(products.value)
                _productsSortedBySerialModel.value = getModels(productsSortedBySerial.value)
            }
        }
    }

    fun onEvent(event: FavoriteProductsScreenEvent) {
        when (event) {
            is FavoriteProductsScreenEvent.OnCollapseColumnStateChanged -> {
                val newModel = productsSortedBySerialModel.value?.toMutableList()
                newModel!![event.index] = newModel[event.index].copy(
                    isOpen = !newModel[event.index].isOpen
                )
                _productsSortedBySerialModel.value = newModel
            }
        }
    }

    private fun getModels(productsSortedBySerial: List<List<GpuProduct>>?): List<ExpandCollapseModel> {
        val models: MutableList<ExpandCollapseModel> = mutableListOf()
        productsSortedBySerial?.forEach { item ->
            models.add(
                ExpandCollapseModel(
                    title = GpuProductsMethods.getNameBySerial(item[0].name)!!,
                    isOpen = false
                )
            )
        }
        return models
    }

    sealed class UiEvent {
        data class OnCollapseColumnStateChanged(val index: Int): UiEvent()
    }
}