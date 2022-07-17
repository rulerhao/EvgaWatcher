package com.rulhouse.evgawatcher.presentation.reminde_screen.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.ReminderMessageEvent
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.ReminderScreenMethods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderMessagesViewModel @Inject constructor(
    private val getDifferentProductsUseCase: GetDifferentProductsUseCase,
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases
) : ViewModel() {

    private val _differenceProducts: MutableState<List<ProductsDifferenceWithReason>?> =
        mutableStateOf(null)
    val differenceProducts: State<List<ProductsDifferenceWithReason>?> = _differenceProducts

    init {
        viewModelScope.launch {
            getDifferentProductsUseCase.getProductsDifferenceWIthReasonFlow().collect {
                _differenceProducts.value = it
            }
        }
    }

    fun onEvent(event: ReminderMessageEvent) {
        when (event) {
            is ReminderMessageEvent.OnGetAll -> {
                viewModelScope.launch {
                    ReminderScreenMethods().setProductsOnGetAll(
                        products = differenceProducts.value,
                        favoriteUseCase = favoriteGpuProductUseCases
                    )
                }
            }
            is ReminderMessageEvent.OnGetIt -> {
                viewModelScope.launch {
                    ReminderScreenMethods().setProductOnGetIt(
                        products = differenceProducts.value,
                        index = event.index,
                        favoriteUseCase = favoriteGpuProductUseCases
                    )
                }
            }
        }
    }
}