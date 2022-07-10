package com.rulhouse.evgawatcher.presentation.product_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _gpuProduct = mutableStateOf(GpuProduct())
    val gpuProduct: State<GpuProduct> = _gpuProduct

    private val _favoriteGpuProduct = mutableStateOf(null as GpuProduct?)
    val favoriteGpuProduct: State<GpuProduct?> = _favoriteGpuProduct

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<GpuProduct>("gpuProduct")?.let { gpuProduct ->
            _gpuProduct.value = gpuProduct
        }
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProductFlowByName(gpuProduct.value.name).collectLatest {
                _favoriteGpuProduct.value = it
            }
        }
    }

    fun onEvent(event: ProductScreenEvent) {
        when(event) {
            is ProductScreenEvent.ToggleFavoriteButton -> {
                viewModelScope.launch {
                    if (favoriteGpuProduct.value == null)
                        favoriteGpuProductUseCases.addFavoriteGpuProduct(gpuProduct.value.copy(favorite = !gpuProduct.value.favorite))
                    else
                        favoriteGpuProductUseCases.deleteFavoriteGpuProduct(favoriteGpuProduct.value!!)
                }
            }
        }
    }

    sealed class UiEvent {
    }
}