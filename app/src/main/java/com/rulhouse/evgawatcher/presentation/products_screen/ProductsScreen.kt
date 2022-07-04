package com.rulhouse.evgawatcher.presentation.products_screen

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.products_screen.boolean_filter_chip.BooleanFilterChipEvent
import com.rulhouse.evgawatcher.presentation.products_screen.boolean_filter_chip.BooleanFilterChipModel
import com.rulhouse.evgawatcher.presentation.products_screen.boolean_filter_chip.BooleanFilterChipsRow
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.MainScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductsScreen(
    mainScreenViewModel: MainScreenViewModel,
    viewModel: ProductsScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ProductsScreenViewModel.UiEvent.OnCollapseColumnStateChanged -> {
                    mainScreenViewModel.onEvent(MainScreenEvent.OnCollapseColumnStateChanged(event.index))
                }
            }
        }
    }

    val showingOutOfStock = mainScreenViewModel.userPreferencesState.value.showingOutOfStock
    val priceAscending = mainScreenViewModel.userPreferencesState.value.priceAscending
    val showingNoPrice = mainScreenViewModel.userPreferencesState.value.showingNoPrice

    val eventList = remember{ mutableStateOf(
        listOf(
            BooleanFilterChipEvent.OnShowingOutOfStockChanged,
            BooleanFilterChipEvent.OnPriceAscendingChanged,
            BooleanFilterChipEvent.OnShowingNoPriceChanged
        )
    )
    }
    Column() {
        BooleanFilterChipsRow(
            list = listOf(
                BooleanFilterChipModel(
                    isOn = showingOutOfStock,
                    text = context.getString(R.string.show_unbuyable)
                ),
                BooleanFilterChipModel(
                    isOn = priceAscending,
                    text = context.getString(R.string.price_ascending)
                ),
                BooleanFilterChipModel(
                    isOn = showingNoPrice,
                    text = context.getString(R.string.show_no_price)
                ),
            ),
            onClick = {
                mainScreenViewModel.onEvent(eventList.value[it])
            }
        )
        LazyColumn(

        ) {
            if (mainScreenViewModel.showingGpuProductsSortedBySerial.value != null && mainScreenViewModel.productsSortedBySerialModel.value != null) {
                itemsIndexed(mainScreenViewModel.showingGpuProductsSortedBySerial.value!!) { index, item ->
                    ExpandCollapseColumn(
                        expandCollapseModel = mainScreenViewModel.productsSortedBySerialModel.value!![index],
                        products = item,
                        onCollapsedStateChanged = {
                            mainScreenViewModel.onEvent(
                                MainScreenEvent.OnCollapseColumnStateChanged(index)
                            )
                        },
                        onClick = {
                            navController.navigate(Screen.ProductScreen.route + "?gpuProduct=${Uri.encode(
                                Gson().toJson(it))}")
                        },
                        onFavoriteClick = {
                            viewModel.onEvent(ProductsScreenEvent.ToggleFavoriteGpuProduct(it))
                        }
                    )
                }
            }
        }
    }
}