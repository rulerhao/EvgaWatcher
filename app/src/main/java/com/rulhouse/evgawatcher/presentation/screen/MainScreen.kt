package com.rulhouse.evgawatcher.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProductType
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.favorite_products_screen.FavoriteProductsScreen
import com.rulhouse.evgawatcher.presentation.product_screen.ProductScreen
import com.rulhouse.evgawatcher.presentation.products_screen.ProductsScreen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            BottomNavigationBar(navController)
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
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
                        FavoriteProductsScreen(navController = navController)
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
    }
}