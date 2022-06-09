package com.rulhouse.evgawatcher.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
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
    }
}