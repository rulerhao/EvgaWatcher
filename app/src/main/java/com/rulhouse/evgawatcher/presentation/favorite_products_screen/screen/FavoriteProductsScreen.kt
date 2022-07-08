package com.rulhouse.evgawatcher.presentation.favorite_products_screen.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.presentation.favorite_products_screen.view_model.FavoriteProductsViewModel
import com.rulhouse.evgawatcher.presentation.products_screen.screen.ProductsScreen

@Composable
fun FavoriteProductsScreen(
    viewModel: FavoriteProductsViewModel = hiltViewModel(),
    navController: NavController
) {
    ProductsScreen(viewModel, navController)
}