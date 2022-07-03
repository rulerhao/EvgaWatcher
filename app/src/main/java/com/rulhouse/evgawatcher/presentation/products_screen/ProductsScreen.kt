package com.rulhouse.evgawatcher.presentation.products_screen

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.MainScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ProductsScreen(
    mainScreenViewModel: MainScreenViewModel,
    viewModel: ProductsScreenViewModel = hiltViewModel(),
    navController: NavController
) {
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

    Column() {
        Row(

        ) {
            BooleanFilterChip(
                isOn =showingOutOfStock,
                onClick = {
                    mainScreenViewModel.onEvent(MainScreenEvent.OnShowingOutOfStockChanged)
                },
                Content = {
                    Text(text = "Show unbuyable")
                }
            )
            BooleanFilterChip(
                isOn = priceAscending,
                onClick = {
                    mainScreenViewModel.onEvent(MainScreenEvent.OnPriceAscendingChanged)
                },
                Content = {
                    Text(text = "Price")
                }
            )
        }
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