package com.rulhouse.evgawatcher.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.GpuProduct
import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    crawlerUseCases: CrawlerUseCases
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
        when(event) {
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
            _productsSortedBySerial.value = GpuProductsMethods.getNamedBySerial(products.value)
            val models: MutableList<ExpandCollapseModel> = mutableListOf()
            productsSortedBySerial.value?.forEachIndexed { index, item ->
                models.add(
                    ExpandCollapseModel(
                        title = item[0].name,
                        isOpen = false
                    )
                )
            }
            _productsSortedBySerialModel.value = models
        }
    }
}