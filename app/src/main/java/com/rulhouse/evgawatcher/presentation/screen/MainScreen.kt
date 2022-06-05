package com.rulhouse.evgawatcher.presentation.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.data.GpuProductType
import com.rulhouse.evgawatcher.presentation.products_screen.ProductsScreen
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.product_screen.ProductScreen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()

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
        composable(
            route = Screen.ProductScreen.route + "?" +
                    "gpuProduct={gpuProduct}",
            arguments = listOf(
                navArgument(
                    name = "gpuProduct"
                ) {

                    type = GpuProductType()
                    Log.d("TestGson", "type = $type")

                }
            )
        ) {
            ProductScreen(navController = navController)
        }
    }
}