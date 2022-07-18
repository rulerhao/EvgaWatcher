package com.rulhouse.evgawatcher.presentation.reminde_screen.util

import android.net.Uri
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.presentation.Screen

class ReminderScreenMethods {
    suspend fun setProductsOnGetAll(products: List<ProductsDifferenceWithReason>?, favoriteUseCase: FavoriteGpuProductUseCases) {
        if (products == null) return
        products.forEach {
            favoriteUseCase.addFavoriteGpuProduct(
                it.productBeCompare.copy(
                    canBeBought = it.productGoCompare.canBeBought,
                    price = it.productGoCompare.price
                )
            )
        }
    }

    suspend fun setProductOnGetIt(products: List<ProductsDifferenceWithReason>?, index: Int, favoriteUseCase: FavoriteGpuProductUseCases) {
        products?.let {
            val differenceProduct = it[index]
            setProductOnGetIt(
                product = differenceProduct,
                favoriteUseCase = favoriteUseCase
            )
        }
    }

    private suspend fun setProductOnGetIt(product: ProductsDifferenceWithReason, favoriteUseCase: FavoriteGpuProductUseCases) {
        favoriteUseCase.addFavoriteGpuProduct(
            getReplaceProduct(product)
        )
    }

    private fun getReplaceProduct(product: ProductsDifferenceWithReason): GpuProduct {
        return product.productBeCompare.copy(
            canBeBought = if (product.reason == DifferenceReason.GetNotBuyable || product.reason == DifferenceReason.GetBuyable) {
                product.productGoCompare.canBeBought
            } else product.productBeCompare.canBeBought,
            price = if (product.reason == DifferenceReason.PriceGetHigher || product.reason == DifferenceReason.PriceGetLower) {
                product.productGoCompare.price
            } else product.productBeCompare.price
        )
    }

    fun navigateToProductScreen(
        differenceProducts: List<ProductsDifferenceWithReason>?,
        navController: NavController,
        index: Int
    ) {
        differenceProducts?.let { productsReason ->
            val favoriteProduct = productsReason[index].productBeCompare
            val crawlerProduct = productsReason[index].productGoCompare
            val navigateProduct = favoriteProduct.copy(
                canBeBought = crawlerProduct.canBeBought,
                price = crawlerProduct.price
            )
            navController.navigate(
                Screen.ProductScreen.route + "?gpuProduct=${
                    Uri.encode(Gson().toJson(navigateProduct))}"
            )
        }
    }

}