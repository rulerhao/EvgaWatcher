package com.rulhouse.evgawatcher.crawler

import android.util.Log
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UpdateShowingNoPriceProduct
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.products_screen.ExpandCollapseModel

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

    fun sortProductsWithPrice(
        products: List<GpuProduct>,
        showingOutOfStock: Boolean,
        priceAscending: Boolean,
        showingNoPrice: Boolean,
    ): List<GpuProduct> {
        var newProducts: List<GpuProduct> = emptyList<GpuProduct>().toMutableList()

        newProducts = getProductsWithOOS(products = products, showingOutOfStock = showingOutOfStock)
        newProducts =
            getShowingPriceProducts(products = newProducts, excludingNoPrice = showingNoPrice)
        newProducts = sortProductsWithPrice(products = newProducts, priceAscending = priceAscending)
        return newProducts
    }

    private fun getProductsWithOOS(
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

    private fun sortProductsWithPrice(
        products: List<GpuProduct>?,
        priceAscending: Boolean,
    ): List<GpuProduct> {
        val newProducts: MutableList<GpuProduct>

        val noPriceProducts = getNoPriceProducts(products)
        val withPriceProducts = getShowingPriceProducts(products, true)
        newProducts =
            if (priceAscending) withPriceProducts.sortedBy { it.price }.toMutableList()
            else withPriceProducts.sortedByDescending { it.price }.toMutableList()
        newProducts.addAll(noPriceProducts)

        newProducts.forEach {
            Log.d("TestProductsPrice", "Price = ${it.price}, name = ${it.name}")
        }
        return newProducts
    }

    private fun getOutOfStockProducts(products: List<GpuProduct>?): List<GpuProduct> {
        if (products == null)
            return emptyList()
        val outOfStockProducts: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        products.forEach {
            if (it.canBeBought == null || it.canBeBought == false)
                outOfStockProducts.add(it)
        }

        return outOfStockProducts
    }

    fun getShowingPriceProducts(
        products: List<GpuProduct>?,
        excludingNoPrice: Boolean
    ): List<GpuProduct> {
        if (products == null)
            return emptyList()
        val ans: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        if (excludingNoPrice) {
            products.forEach {
                if (it.price != null && it.price != 0) {
                    ans.add(it)
                }
            }
            return ans
        } else {
            return products
        }
    }

    fun getNoPriceProducts(products: List<GpuProduct>?): List<GpuProduct> {
        if (products == null)
            return emptyList()

        val ans: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        products.forEach {
            if (it.price == null || it.price == 0) {
                ans.add(it)
            }
        }
        return ans
    }

    fun getFavoriteProducts(
        products: List<GpuProduct>?,
        favoriteProducts: List<GpuProduct>?
    ): List<GpuProduct> {
        if (products == null || favoriteProducts == null) return emptyList()

        val newProducts = emptyList<GpuProduct>().toMutableList()
        val productsName = emptyList<String>().toMutableList()
        products.forEach {
            productsName.add(it.name)
        }
        favoriteProducts.forEach { thisFavoriteProducts ->
            if (thisFavoriteProducts.favorite) {
                val indexInProducts = productsName.indexOf(thisFavoriteProducts.name)
                newProducts.add(products[indexInProducts].copy(favorite = true))
            }
        }

        return newProducts
    }

    fun getProductsWithFavorites(
        products: List<GpuProduct>?,
        favoriteProducts: List<GpuProduct>?
    ): List<GpuProduct> {
        if (products == null) return emptyList()

        val newProducts = products.toMutableList()
        val productsName = emptyList<String>().toMutableList()
        products.forEach {
            productsName.add(it.name)
        }
        favoriteProducts?.let {
            it.forEach { thisFavoriteProduct ->
                if (thisFavoriteProduct.favorite) {
                    val indexInProducts = productsName.indexOf(thisFavoriteProduct.name)
                    if (indexInProducts != -1)
                        newProducts[indexInProducts] =
                            products[indexInProducts].copy(favorite = true)
                }
            }
        }

        return newProducts
    }

    fun getCollapsedModels(productsSortedBySerial: List<List<GpuProduct>>?): List<ExpandCollapseModel> {
        if (productsSortedBySerial == null) return emptyList()

        val models: MutableList<ExpandCollapseModel> =
            emptyList<ExpandCollapseModel>().toMutableList()
        productsSortedBySerial.forEach { item ->
            models.add(
                ExpandCollapseModel(
                    title = getNameBySerial(item[0].name)!!,
                    isOpen = false
                )
            )
        }

        return models
    }
}