package com.rulhouse.evgawatcher.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.ui.graphics.vector.ImageVector
import com.rulhouse.evgawatcher.R

sealed class Screen(val route: String, val image: ImageVector, @StringRes val resourceId: Int) {
    object AllProductsScreenScreen : Screen("all_products_screen", Icons.Filled.ProductionQuantityLimits, R.string.all_products)
    object FavoriteProductsScreen : Screen("favorite_products_screen", Icons.Filled.Favorite, R.string.favorite_products)
    object ProductScreen : Screen("product_screen", Icons.Filled.ProductionQuantityLimits, R.string.product)
}