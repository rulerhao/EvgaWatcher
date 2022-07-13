package com.rulhouse.evgawatcher.methods.favorite_products.data

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class GpuProductType: NavType<GpuProduct>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: GpuProduct) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): GpuProduct {
        return bundle.getParcelable(key)!!
    }

    override fun parseValue(value: String): GpuProduct {
        return Gson().fromJson(value, GpuProduct::class.java)
    }
}