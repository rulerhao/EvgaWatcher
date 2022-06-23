package com.rulhouse.evgawatcher.renew_favorite_products

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RenewFavoriteProductsBroadcastReceiver: BroadcastReceiver() {
    companion object {
        const val DIFFERENT_FAVORITE_PRODUCTS = "different favorite products"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.extras != null) {
                val products = intent.extras!!.get(DIFFERENT_FAVORITE_PRODUCTS)
            }
        }
    }
}