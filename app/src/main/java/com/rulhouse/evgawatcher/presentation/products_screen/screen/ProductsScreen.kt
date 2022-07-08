package com.rulhouse.evgawatcher.presentation.products_screen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.presentation.product_screen.item.BooleanFilterChipsImpl
import com.rulhouse.evgawatcher.presentation.products_screen.item.products_list.ProductsCardList
import com.rulhouse.evgawatcher.presentation.products_screen.view_model.ProductsScreenViewModel

@Composable
fun ProductsScreen(
    viewModel: ProductsScreenViewModel,
    navController: NavController
) {
    Column() {
        BooleanFilterChipsImpl(
            viewModel.userPreferencesState.value,
            onEvent = { viewModel.onEvent(it) }
        )
        ProductsCardList(
            viewModel = viewModel,
            navController = navController
        )
    }
}