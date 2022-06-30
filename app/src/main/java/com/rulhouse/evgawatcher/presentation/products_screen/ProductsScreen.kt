package com.rulhouse.evgawatcher.presentation.products_screen

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
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
    Column() {
        Row(

        ) {
            BooleanFilterChip(
                isOn = mainScreenViewModel.showingOutOfStock.value,
                onClick = {
                    mainScreenViewModel.onEvent(MainScreenEvent.OnShowingOutOfStockChanged)
                },
                Content = {
                    Text(text = "Show unbuyable")
                }
            )
            BooleanFilterChip(
                isOn = mainScreenViewModel.priceAscending.value,
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
            if (mainScreenViewModel.productsSortedBySerial.value != null && mainScreenViewModel.productsSortedBySerialModel.value != null) {
                itemsIndexed(mainScreenViewModel.productsSortedBySerial.value!!) { index, item ->
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