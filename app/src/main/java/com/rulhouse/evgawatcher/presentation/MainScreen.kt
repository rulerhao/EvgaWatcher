package com.rulhouse.evgawatcher.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AllProductsScreenScreen.route
    ) {
        composable(route = Screen.AllProductsScreenScreen.route) {
            ProductsScreen(
                viewModel
            )
        }
        composable(route = Screen.FavoriteProductsScreen.route) {

        }
    }
}