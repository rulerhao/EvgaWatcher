package com.rulhouse.evgawatcher.presentation.products_screen

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
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.MainScreenViewModel
import kotlinx.coroutines.flow.collectLatest

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

    Log.d("FavoriteChange", "Recompose")
    Log.d("FavoriteChange", "Products = ${mainScreenViewModel.favoriteProducts}")
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
                    }
                )
            }
        }
    }
}