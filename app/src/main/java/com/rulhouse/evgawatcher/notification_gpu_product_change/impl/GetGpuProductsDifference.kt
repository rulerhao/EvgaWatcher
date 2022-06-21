package com.rulhouse.evgawatcher.notification_gpu_product_change.impl

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference

class GetGpuProductsDifference {
    fun getDifference(favoriteGpuProducts: List<GpuProduct>?, crawlerGpuProducts: List<GpuProduct>?): List<ProductsDifference> {
        if (favoriteGpuProducts == null || crawlerGpuProducts == null) {
            return emptyList()
        }
        val tempDifferenceGpuProducts = emptyList<ProductsDifference>().toMutableList()
        favoriteGpuProducts.forEach { favoriteProduct ->
            crawlerGpuProducts.forEach { crawlerProduct ->
                if (favoriteProduct.name == crawlerProduct.name) {
                    val buyableDifference = buyableDifference(favoriteProduct, crawlerProduct)
                    if (buyableDifference != null) {
                        tempDifferenceGpuProducts.add(buyableDifference)
                    }
                    val priceDifference = priceDifference(favoriteProduct, crawlerProduct)
                    if (priceDifference != null) {
                        tempDifferenceGpuProducts.add(priceDifference)
                    }
                }
            }
        }
        return tempDifferenceGpuProducts
    }

    private fun buyableDifference(favoriteProduct: GpuProduct, crawlerProduct: GpuProduct): ProductsDifference? {
        if (favoriteProduct.canBeBought != crawlerProduct.canBeBought) {
            if (crawlerProduct.canBeBought == true)
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.GetBuyable)
            else if (crawlerProduct.canBeBought == false)
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.GetNotBuyable)
        }
        return null
    }

    private fun priceDifference(favoriteProduct: GpuProduct, crawlerProduct: GpuProduct): ProductsDifference? {
        if (favoriteProduct.price != crawlerProduct.price) {
            if (favoriteProduct.price!! > crawlerProduct.price!!)
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.PriceGetLower)
            else
                return ProductsDifference(gpuProduct = crawlerProduct, reason = DifferenceReason.PriceGetHigher)
        }
        return null
    }
}