package com.rulhouse.evgawatcher.presentation.products_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case.FavoriteGpuProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases
) : ViewModel() {

    private val _favoriteProducts: MutableState<List<GpuProduct>?> = mutableStateOf(emptyList())
    val favoriteProducts: State<List<GpuProduct>?> = _favoriteProducts

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProducts().collectLatest {
                _favoriteProducts.value = it
            }
        }
    }

    fun onEvent(event: ProductsScreenEvent) {
        when (event) {
            is ProductsScreenEvent.ToggleFavoriteGpuProduct -> {
                if (favoriteProducts.value != null) {
                    favoriteProducts.value!!.forEach { favoriteProduct ->
                        if (favoriteProduct.name == event.gpuProduct.name) {
                            viewModelScope.launch {
                                favoriteGpuProductUseCases.deleteFavoriteGpuProduct(favoriteProduct)
                            }
                            return
                        }
                    }
                    viewModelScope.launch {
                        favoriteGpuProductUseCases.addFavoriteGpuProduct(
                            event.gpuProduct.copy(
                                favorite = true
                            )
                        )
                    }
                }
            }
            is ProductsScreenEvent.ClickGpuProduct -> {}
            is ProductsScreenEvent.OnCollapseColumnStateChanged -> {
                emitOnCollapseColumnStateChanged(event.index)
            }
        }
    }

    private fun emitOnCollapseColumnStateChanged(index: Int) {
        viewModelScope.launch {
            try {
                _eventFlow.emit(
                    UiEvent.OnCollapseColumnStateChanged(index)
                )
            } catch (e: Exception) {

            }
        }
    }

    sealed class UiEvent {
        data class OnCollapseColumnStateChanged(val index: Int) : UiEvent()
    }
}