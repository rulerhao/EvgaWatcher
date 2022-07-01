package com.rulhouse.evgawatcher.crawler

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
                    gpuProductsNameList =
                        gpuProductsNameList.sortedBy { pattern.find(it[0].name)?.value?.lowercase() }
                            .toMutableList()
                } else {
                    val index = nameList.indexOf(str)
                    gpuProductsNameList[index].add(product)
                }
            }
        }
        return gpuProductsNameList
    }

    fun sortProducts(
        products: List<GpuProduct>,
        showingOutOfStock: Boolean,
        priceAscending: Boolean
    ): List<GpuProduct> {
        var newProducts: List<GpuProduct> = emptyList<GpuProduct>().toMutableList()

        newProducts = getProducts(products = products, showingOutOfStock = showingOutOfStock)
        newProducts = sortProducts(products = newProducts, priceAscending = priceAscending)
        return newProducts
    }

    private fun getProducts(
        products: List<GpuProduct>?,
        showingOutOfStock: Boolean
    ): List<GpuProduct> {
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

    private fun sortProducts(
        products: List<GpuProduct>?,
        priceAscending: Boolean
    ): List<GpuProduct> {
        var outOfStockProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        var nonOutOfStockProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        var newProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()

        outOfStockProducts = getOutOfStockProducts(products).toMutableList()
        nonOutOfStockProducts = getProducts(products, false).toMutableList()
        newProducts =
            if (priceAscending) nonOutOfStockProducts.sortedBy { it.price }.toMutableList()
            else nonOutOfStockProducts.sortedByDescending { it.price }.toMutableList()
        newProducts.addAll(outOfStockProducts)

        return newProducts
    }

    private fun getOutOfStockProducts(products: List<GpuProduct>?): List<GpuProduct> {
        if (products == null)
            return emptyList()
        val outOfStockProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        products.forEach {
            if (it.canBeBought == null)
                outOfStockProducts.add(it)
            else if (it.canBeBought == false)
                outOfStockProducts.add(it)
        }

        return outOfStockProducts
    }
}