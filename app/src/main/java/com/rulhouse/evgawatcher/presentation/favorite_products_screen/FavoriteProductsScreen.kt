package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.products_screen.ExpandCollapseColumn
import com.rulhouse.evgawatcher.presentation.products_screen.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.products_screen.ProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.MainScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteProductsScreen(
    viewModel: FavoriteProductsScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is FavoriteProductsScreenViewModel.UiEvent.OnCollapseColumnStateChanged -> {
                    viewModel.onEvent(FavoriteProductsScreenEvent.OnCollapseColumnStateChanged(event.index))
                }
            }
        }
    }

    LazyColumn(

    ) {
        if (viewModel.productsSortedBySerial.value != null && viewModel.productsSortedBySerialModel.value != null) {
            itemsIndexed(viewModel.productsSortedBySerial.value!!) { index, item ->
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
                        Log.d("TestDelete", "onFavoriteClick = ${it.name}")
                        viewModel.onEvent(FavoriteProductsScreenEvent.ToggleFavoriteGpuProduct(it))
                    }
                )
            }
        }
    }
}