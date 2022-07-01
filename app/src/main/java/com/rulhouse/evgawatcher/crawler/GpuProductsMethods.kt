package com.rulhouse.evgawatcher.crawler

import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UpdatePriceAscending
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UpdateShowingOutOfStock
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct

object GpuProductsMethods {
    private val regex = ".* \\d{4}( Ti)?"

    fun getNameBySerial(str: String): String? {
        val pattern = Regex(regex)
        val found = pattern.find(str)
        return found?.value
    }

    fun getNamesBySerial(gpuProducts: List<GpuProduct>?): List<List<GpuProduct>> {
        var nameList = mutableListOf<String>()
        var gpuProductsNameList = mutableListOf<MutableList<GpuProduct>>()
        gpuProducts?.forEach { product ->
            val pattern = Regex(regex)
            val found = pattern.find(product.name)
            val str = found?.value
            if (str != null) {
                if (!nameList.contains(str)) {
                    nameList.add(str)
                    nameList = nameList.sortedBy { it.lowercase() }.toMutableList()
                    gpuProductsNameList.add(mutableListOf(product))
                    gpuProductsNameList = gpuProductsNameList.sortedBy { pattern.find(it[0].name)?.value?.lowercase() }.toMutableList()
                } else {
                    val index = nameList.indexOf(str)
                    gpuProductsNameList[index].add(product)
                }
            }
        }
        return gpuProductsNameList
    }

    fun sortProducts(products: List<GpuProduct>, showingOutOfStock: Boolean, priceAscending: Boolean): List<GpuProduct> {
        val newProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()

        if ()
    }

    private fun getOutOfStockProducts(products: List<GpuProduct>?, showingOutOfStock: Boolean): List<GpuProduct> {
        if (products == null)
            return emptyList()
        if (products.isEmpty())
            return emptyList()

        if (showingOutOfStock) {
            return products
        } else {
            val newProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
            products.forEach {
                if (it.canBeBought != null && it.canBeBought) {
                    newProducts.add(it)
                }
            }
            return newProducts
        }
    }

    private fun sortProducts(products: List<GpuProduct>?, priceAscending: Boolean): List<GpuProduct> {
        val outOfStockProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        var noOutOfStockProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        val newProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()

        noOutOfStockProducts = getOutOfStockProducts(products, false).toMutableList()

    }

    private fun getOutOfStockProducts(products: List<GpuProduct>?): List<GpuProduct> {
        if (products == null)
            return emptyList()
        val outOfStockProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
    }
}