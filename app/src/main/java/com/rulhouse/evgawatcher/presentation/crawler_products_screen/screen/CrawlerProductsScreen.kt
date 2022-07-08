package com.rulhouse.evgawatcher.presentation.crawler_products_screen.screen

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.view_model.CrawlerProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.product_screen.item.BooleanFilterChipsImpl
import com.rulhouse.evgawatcher.presentation.products_screen.item.ExpandCollapseColumn
import com.rulhouse.evgawatcher.presentation.products_screen.item.products_list.ProductsCardList
import com.rulhouse.evgawatcher.presentation.products_screen.screen.ProductsScreen

@Composable
fun CrawlerProductsScreen(
    viewModel: CrawlerProductsScreenViewModel,
    navController: NavController
) {
    ProductsScreen(viewModel, navController)
}