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
                    gpuProductsNameList = gpuProductsNameList.sortedBy { pattern.find(it[0].name)?.value?.lowercase() }.toMutableList()
                } else {
                    val index = nameList.indexOf(str)
                    gpuProductsNameList[index].add(product)
                }
            }
        }
        return gpuProductsNameList
    }
}