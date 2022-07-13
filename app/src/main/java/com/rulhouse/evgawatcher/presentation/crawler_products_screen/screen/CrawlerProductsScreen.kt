package com.rulhouse.evgawatcher.presentation.crawler_products_screen.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.event.CrawlerProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.view_model.CrawlerProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.products_screen.screen.ProductsScreen

@Composable
fun CrawlerProductsScreen(
    viewModel: CrawlerProductsScreenViewModel,
    navController: NavController
) {
    val swipeRefreshState = rememberSwipeRefreshState(viewModel.refreshingCrawler.value)
    SwipeRefresh(
        modifier = Modifier,
        state = swipeRefreshState,
        onRefresh = { viewModel.onEvent(CrawlerProductsScreenEvent.OnRefresh) },
        indicatorPadding = PaddingValues(80.dp)
    ) {
        ProductsScreen(
            viewModel,
            navController,
            productState = viewModel.productsState.value
        )
    }
}