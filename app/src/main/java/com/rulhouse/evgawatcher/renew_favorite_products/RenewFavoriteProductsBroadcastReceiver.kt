package com.rulhouse.evgawatcher.renew_favorite_products

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference

class RenewFavoriteProductsBroadcastReceiver: BroadcastReceiver() {
    companion object {
        const val DIFFERENT_FAVORITE_PRODUCTS = "different favorite products"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.extras != null) {
                val productsDifference = intent.getParcelableExtra<ProductsDifference>(DIFFERENT_FAVORITE_PRODUCTS)
            }
        }
    }
}