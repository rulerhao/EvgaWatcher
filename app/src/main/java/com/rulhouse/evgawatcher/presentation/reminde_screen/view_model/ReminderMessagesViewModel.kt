package com.rulhouse.evgawatcher.presentation.reminde_screen.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.ReminderMessageEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderMessagesViewModel @Inject constructor(
    private val getDifferentProductsUseCase: GetDifferentProductsUseCase,
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases
): ViewModel() {

    private val _differenceProducts: MutableState<List<ProductsDifferenceWithReason>?> =
        mutableStateOf(null)
    val differenceProducts: State<List<ProductsDifferenceWithReason>?> = _differenceProducts

    init {
        viewModelScope.launch {
            _differenceProducts.value = getDifferentProductsUseCase.getProductsDifferenceWIthReason()
        }
    }

    fun onEvent(event: ReminderMessageEvent) {
        when (event) {
            is ReminderMessageEvent.OnGetAll -> {
                viewModelScope.launch {
                    differenceProducts.value?.forEach {
                        favoriteGpuProductUseCases.addFavoriteGpuProduct(it.productBeCompare.copy(
                            canBeBought = it.productGoCompare.canBeBought,
                            price = it.productGoCompare.price
                        ))
                        _differenceProducts.value = null
                    }
                }
            }
            is ReminderMessageEvent.OnGetIt -> {
                viewModelScope.launch {
                    differenceProducts.value?.let {
                        val differenceProduct = it[event.index]
                        favoriteGpuProductUseCases.addFavoriteGpuProduct(differenceProduct.productBeCompare.copy(
                            canBeBought = differenceProduct.productGoCompare.canBeBought,
                            price = differenceProduct.productGoCompare.price
                        ))
                        val newDifferenceProducts = differenceProducts.value?.toMutableList()
                        newDifferenceProducts?.remove(differenceProduct)
                        _differenceProducts.value = newDifferenceProducts
                    }
                }
            }
        }
    }
}