package com.rulhouse.evgawatcher.presentation.reminde_screen.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
import com.rulhouse.evgawatcher.presentation.products_screen.model.ProductState
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.ReminderMessageEvent
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.CrawlerState
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.ReminderScreenCrawlerState
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.ReminderScreenMethods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
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

    private var getProductsDifferenceWIthReasonFlow: Flow<List<ProductsDifferenceWithReason>>? = null

    private val _crawlerState: MutableState<CrawlerState> =
        mutableStateOf(CrawlerState.Waiting)
    val crawlerState: State<CrawlerState> = _crawlerState

    init {
        viewModelScope.launch {
            setProductsFlow()
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
            is ReminderMessageEvent.OnRefresh -> {
                viewModelScope.launch {
                    setProductsFlow()
                }
            }
        }
    }

    private suspend fun setProductsFlow() {
        try {
            _crawlerState.value = CrawlerState.Waiting
            getProductsDifferenceWIthReasonFlow = getDifferentProductsUseCase.getProductsDifferenceWIthReasonFlow()
            getProductsDifferenceWIthReasonFlow?.let{ flow ->
                flow.collect {
                    _differenceProducts.value = it
                    _crawlerState.value = CrawlerState.Success
                }
            }
        } catch(e: Exception) {
            e.printStackTrace()
            _crawlerState.value = CrawlerState.Failure
        }
    }
}