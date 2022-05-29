package com.rulhouse.evgawatcher.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

//    NavHost(
//        navController = navController,
//        startDestination = Screen.AllProductsScreenScreen.route
//    ) {
//        composable(route = Screen.AllProductsScreenScreen.route) {
//            SplashScreen(navController = navController)
//        }
//        composable(route = Screen.FavoriteProductsScreen.route) {
//            UserScreen(navController = navController)
//        }
//    }
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        val products = viewModel.products.value
        if (products != null) {
            items(products) { item ->
                GpuProductItem(
                    item = item
                )
            }
        }
    }
}