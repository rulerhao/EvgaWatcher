package com.rulhouse.evgawatcher.presentation.favorite_products_screen.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.presentation.favorite_products_screen.view_model.FavoriteProductsViewModel
import com.rulhouse.evgawatcher.presentation.products_screen.screen.ProductsScreen

@Composable
fun FavoriteProductsScreen(
    viewModel: FavoriteProductsViewModel = hiltViewModel(),
    navController: NavController
) {
    ProductsScreen(
        title = stringResource(id = R.string.favorite_products_list),
        viewModel = viewModel,
        navController = navController
    )
}