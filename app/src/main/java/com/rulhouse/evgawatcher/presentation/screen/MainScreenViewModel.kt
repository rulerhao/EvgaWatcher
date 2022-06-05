package com.rulhouse.evgawatcher.presentation.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oucare.bbt_oucare.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.data.InvalidFavoriteGpuProductException
import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.presentation.ExpandCollapseModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
            products.value?.forEach { product ->
                try {
                    favoriteGpuProductUseCases.addFavoriteGpuProduct(product)
                } catch (e: InvalidFavoriteGpuProductException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
    }
}