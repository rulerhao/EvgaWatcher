package com.rulhouse.evgawatcher.presentation.favorite_products_screen.screen

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
import com.rulhouse.evgawatcher.presentation.favorite_products_screen.FavoriteProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.favorite_products_screen.FavoriteProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.favorite_products_screen.view_model.FavoriteProductsViewModel
import com.rulhouse.evgawatcher.presentation.product_screen.item.BooleanFilterChipsImpl
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteProductsScreen(
    viewModel: FavoriteProductsViewModel = hiltViewModel(),
    navController: NavController
) {

    Column() {
        BooleanFilterChipsImpl(
            viewModel.userPreferencesState.value,
            onEvent = { viewModel.onEvent(it) }
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
                            navController.navigate(
                                Screen.ProductScreen.route + "?gpuProduct=${
                                    Uri.encode(
                                        Gson().toJson(it))}")
                        },
                        onFavoriteClick = {
                            viewModel.onEvent(
                                ProductsScreenEvent.ToggleFavoriteGpuProduct(
                                    it
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}