package com.rulhouse.evgawatcher.presentation

sealed class Screen(val route: String) {
    object AllProductsScreenScreen : Screen("all_products_screen")
    object FavoriteProductsScreen : Screen("favorite_products_screen")
    object ProductScreen : Screen("product_screen")
}