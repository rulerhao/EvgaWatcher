package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.screen.ExpandCollapseColumn
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.boolean_filter_chip.BooleanFilterChipEvent
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.boolean_filter_chip.BooleanFilterChipModel
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.boolean_filter_chip.BooleanFilterChipsRow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteProductsScreen(
    viewModel: FavoriteProductsScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is FavoriteProductsScreenViewModel.UiEvent.OnCollapseColumnStateChanged -> {
                    viewModel.onEvent(FavoriteProductsScreenEvent.OnCollapseColumnStateChanged(event.index))
                }
            }
        }
    }

    val showingOutOfStock = viewModel.userPreferencesState.value.showingOutOfStock
    val priceAscending = viewModel.userPreferencesState.value.priceAscending
    val showingNoPrice = viewModel.userPreferencesState.value.showingNoPrice

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
                viewModel.onEvent(eventList.value[it])
            }
        )
        LazyColumn(

        ) {
            if (viewModel.showingGpuProductsSortedBySerial.value != null && viewModel.productsSortedBySerialModel.value != null) {
                itemsIndexed(viewModel.showingGpuProductsSortedBySerial.value!!) { index, item ->
                    ExpandCollapseColumn(
                        expandCollapseModel = viewModel.productsSortedBySerialModel.value!![index],
                        products = item,
                        onCollapsedStateChanged = {
                            viewModel.onEvent(
                                FavoriteProductsScreenEvent.OnCollapseColumnStateChanged(index)
                            )
                        },
                        onClick = {
                            navController.navigate(
                                Screen.ProductScreen.route + "?gpuProduct=${
                                    Uri.encode(
                                        Gson().toJson(it))}")
                        },
                        onFavoriteClick = {
                            viewModel.onEvent(FavoriteProductsScreenEvent.ToggleFavoriteGpuProduct(it))
                        }
                    )
                }
            }
        }
    }
}