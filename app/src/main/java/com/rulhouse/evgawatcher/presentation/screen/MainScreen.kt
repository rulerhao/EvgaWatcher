package com.rulhouse.evgawatcher.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rulhouse.evgawatcher.presentation.products_screen.ProductsScreen
import com.rulhouse.evgawatcher.presentation.Screen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AllProductsScreenScreen.route
    ) {
        composable(route = Screen.AllProductsScreenScreen.route) {
            ProductsScreen(
                viewModel,
                navController = navController
            )
        }
        composable(route = Screen.FavoriteProductsScreen.route) {

        }
        composable(route = Screen.ProductScreen.route) {

        }
    }
}