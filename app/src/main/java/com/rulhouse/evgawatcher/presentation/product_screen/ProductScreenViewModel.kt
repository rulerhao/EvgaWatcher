package com.rulhouse.evgawatcher.presentation.product_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _gpuProduct = mutableStateOf(GpuProduct())
    val gpuProduct: State<GpuProduct> = _gpuProduct

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<GpuProduct>("gpuProduct")?.let { gpuProduct ->
            _gpuProduct.value = gpuProduct
        }
    }

    sealed class UiEvent {
    }
}