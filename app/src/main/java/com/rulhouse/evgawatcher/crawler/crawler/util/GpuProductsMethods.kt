package com.rulhouse.evgawatcher.crawler.crawler.util

import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.products_screen.item.expand_collapse_column.model.ExpandCollapseModel

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

    fun getToggledSortedModels(
        index: Int,
        modelList: List<ExpandCollapseModel>?
    ): List<ExpandCollapseModel> {
        if (modelList == null) return emptyList()

        val newModel = modelList.toMutableList()
        newModel[index] = newModel[index].copy(
            isOpen = !newModel[index].isOpen
        )
        return newModel
    }

    fun getToggledAllModels(
        index: Int,
        allModels: List<ExpandCollapseModel>?,
        sortedModels: List<ExpandCollapseModel>?
    ): List<ExpandCollapseModel> {
        if (allModels == null) return emptyList()
        if (sortedModels == null) return allModels

        allModels.forEachIndexed { itIndex, it ->
            if (it.title == sortedModels[index].title) {
                allModels[itIndex].isOpen = sortedModels[index].isOpen
            }
        }

        return allModels
    }

    fun getNewModelsBySerial(models: List<ExpandCollapseModel>?, allModels: List<ExpandCollapseModel>?): List<ExpandCollapseModel> {
        if (models == null || models.isEmpty()) return emptyList()
        if (allModels == null || allModels.isEmpty()) return models

        val newModels = models.toMutableList()
        allModels.forEach { modelOfAllModels ->
            newModels.forEach { modelOfNewModels ->
                if (modelOfNewModels.title == modelOfAllModels.title) {
                    if (modelOfNewModels.isOpen != modelOfAllModels.isOpen) {
                        modelOfNewModels.isOpen = modelOfAllModels.isOpen
                    }
                }
            }
        }
        return newModels
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
            getShowingPriceProducts(products = newProducts, showingNoPrice = showingNoPrice)
        newProducts = sortProductsWithPrice(products = newProducts, priceAscending = priceAscending)
        return newProducts
    }

    private fun getRegexName(name: String): String? {
        return Regex(regex).find(name)?.value
    }

    fun getModels(productsSortedBySerial: List<List<GpuProduct>>?, models: List<ExpandCollapseModel>?): List<ExpandCollapseModel> {
        if (productsSortedBySerial == null) return emptyList()
        if (models == null) {
            return getCollapsedModels(productsSortedBySerial)
        }

        val sortedModels = emptyList<ExpandCollapseModel>().toMutableList()
        productsSortedBySerial.forEach {
            val name = getRegexName(it[0].name)
            if (name != null) {
                models.forEach { model ->
                    if (model.title == name) {
                        sortedModels.add(model)
                    }
                }
            }
        }
        return sortedModels
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
        val withPriceProducts = getShowingPriceProducts(products, false)
        newProducts =
            if (priceAscending) withPriceProducts.sortedBy { it.price }.toMutableList()
            else withPriceProducts.sortedByDescending { it.price }.toMutableList()
        newProducts.addAll(noPriceProducts)
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
        showingNoPrice: Boolean
    ): List<GpuProduct> {
        if (products == null)
            return emptyList()
        val ans: MutableList<GpuProduct> = emptyList<GpuProduct>().toMutableList()
        if (!showingNoPrice) {
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