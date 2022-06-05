package com.rulhouse.evgawatcher.presentation.products_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(

): ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: ProductsScreenEvent) {
        when(event) {
            is ProductsScreenEvent.ToggleFavoriteGpuProduct -> {}
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
        data class OnCollapseColumnStateChanged(val index: Int): UiEvent()
    }
}