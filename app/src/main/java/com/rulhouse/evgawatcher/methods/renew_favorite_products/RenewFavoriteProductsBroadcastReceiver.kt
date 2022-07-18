package com.rulhouse.evgawatcher.methods.renew_favorite_products

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class RenewFavoriteProductsBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val DIFFERENT_FAVORITE_PRODUCTS = "different favorite products"
    }

    @Inject
    lateinit var favoriteGpuProductUseCases: FavoriteGpuProductUseCases

    private val myScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.extras != null) {
                val productsDifference =
                    intent.getParcelableExtra<ProductsDifference>(DIFFERENT_FAVORITE_PRODUCTS)
                modifyFavoriteProduct(productsDifference)
            }
        }
    }

    private fun modifyFavoriteProduct(productsDifference: ProductsDifference?) {
        if (productsDifference != null) {
            val newGpuProduct = productsDifference.gpuProduct
            val favoriteGpuProduct = getFavoriteProductByName(newGpuProduct.name)
            addProduct(newGpuProduct, favoriteGpuProduct)
        }
    }

    private fun getFavoriteProductByName(name: String): GpuProduct? {
        var favoriteGpuProduct: GpuProduct?
        runBlocking {
            favoriteGpuProduct = favoriteGpuProductUseCases.getFavoriteGpuProductByName(name)
        }
        return favoriteGpuProduct
    }

    private fun addProduct(newGpuProduct: GpuProduct, favoriteGpuProduct: GpuProduct?) {
        if (favoriteGpuProduct != null) {
            val tempFavoriteGpuProduct = newGpuProduct.copy(favorite = true, id = favoriteGpuProduct.id)
            myScope.launch {
                favoriteGpuProductUseCases.addFavoriteGpuProduct(tempFavoriteGpuProduct)
            }
        }
    }
}