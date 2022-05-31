package com.rulhouse.evgawatcher.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
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
    val expandCollapseModel = remember {
        mutableStateOf(
            ExpandCollapseModel(
                1, "GpuProduct",
                needsPlusButton = false,
                isOpen = true
            )
        )
    }
    val products = viewModel.products.value
    if (products != null) {
        ExpandCollapseColumn(
            expandCollapseModel = expandCollapseModel.value,
            products = products,
            onCollapsedStateChanged = {
                expandCollapseModel.value = expandCollapseModel.value.copy(isOpen = it)
            }
        )
    }
//    LazyColumn(
//        modifier = Modifier
//            .padding(16.dp)
//    ) {
//        val products = viewModel.products.value
//        if (products != null) {
//            items(products) { item ->
//                GpuProductItem(
//                    item = item
//                )
//            }
//        }
//    }
}