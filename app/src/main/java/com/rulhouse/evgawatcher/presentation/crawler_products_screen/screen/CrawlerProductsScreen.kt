package com.rulhouse.evgawatcher.presentation.crawler_products_screen.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.view_model.CrawlerProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.products_screen.screen.ProductsScreen

@Composable
fun CrawlerProductsScreen(
    viewModel: CrawlerProductsScreenViewModel,
    navController: NavController
) {
    ProductsScreen(
        viewModel,
        navController,
        loadingCrawlerState = viewModel.loadingCrawlerState.value,
        loadingRepositoryState = viewModel.loadingRepositoryState.value
    )
}