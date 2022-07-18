package com.rulhouse.evgawatcher.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProductType
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.favorite_products_screen.screen.FavoriteProductsScreen
import com.rulhouse.evgawatcher.presentation.product_screen.ProductScreen
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.screen.CrawlerProductsScreen
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.view_model.CrawlerProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.reminde_screen.screen.RemindersScreen
import com.rulhouse.evgawatcher.presentation.reminde_screen.view_model.ReminderMessagesViewModel

@Composable
fun MainScreen(
) {
    val viewModel: CrawlerProductsScreenViewModel = hiltViewModel()
    val reminderMessagesViewModel: ReminderMessagesViewModel = hiltViewModel()

    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.AllProductsScreenScreen.route
        ) {
            composable(route = Screen.AllProductsScreenScreen.route) {
                CrawlerProductsScreen(
                    viewModel,
                    navController = navController
                )
            }
            composable(route = Screen.FavoriteProductsScreen.route) {
                FavoriteProductsScreen(navController = navController)
            }
            composable(route = Screen.RemindersScreen.route) {
                RemindersScreen(
                    navController = navController,
                    reminderMessagesViewModel = reminderMessagesViewModel
                )
            }
            composable(
                route = Screen.ProductScreen.route + "?" +
                        "gpuProduct={gpuProduct}",
                arguments = listOf(
                    navArgument(
                        name = "gpuProduct"
                    ) {

                        type = GpuProductType()

                    }
                )
            ) {
                ProductScreen(navController = navController)
            }
        }
    }
}