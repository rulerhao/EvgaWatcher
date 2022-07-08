package com.rulhouse.evgawatcher.presentation.products_screen.item.products_list

import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.products_screen.item.ExpandCollapseColumn
import com.rulhouse.evgawatcher.presentation.products_screen.item.expand_collapse_column.model.ExpandCollapseModel
import com.rulhouse.evgawatcher.presentation.products_screen.view_model.ProductsScreenViewModel

@Composable
fun ProductsCardList(
    viewModel: ProductsScreenViewModel,
    navController: NavController
) {
    val productsSortedBySerial = viewModel.showingGpuProductsSortedBySerial.value
    val sortedModels = viewModel.productsSortedBySerialModel.value

    LazyColumn(

    ) {
        if (productsSortedBySerial != null && sortedModels != null) {
            itemsIndexed(productsSortedBySerial) { index, item ->
                ExpandCollapseColumn(
                    expandCollapseModel = sortedModels[index],
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
                        viewModel.onEvent(ProductsScreenEvent.ToggleFavoriteGpuProduct(it))
                    }
                )
            }
        }
    }
}