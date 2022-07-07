package com.rulhouse.evgawatcher.presentation.crawler_products_screen.screen

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.boolean_filter_chip.BooleanFilterChipModel
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.boolean_filter_chip.BooleanFilterChipsRow
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.view_model.CrawlerProductsScreenViewModel

@Composable
fun CrawlerProductsScreen(
    viewModel: CrawlerProductsScreenViewModel,
    navController: NavController
) {

    val context = LocalContext.current

    val showingOutOfStock = viewModel.userPreferencesState.value.showingOutOfStock
    val priceAscending = viewModel.userPreferencesState.value.priceAscending
    val showingNoPrice = viewModel.userPreferencesState.value.showingNoPrice

    val eventList = remember{ mutableStateOf(
        listOf(
            ProductsScreenEvent.OnShowingOutOfStockChanged,
            ProductsScreenEvent.OnPriceAscendingChanged,
            ProductsScreenEvent.OnShowingNoPriceChanged
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
                                ProductsScreenEvent.OnCollapseColumnStateChanged(index)
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