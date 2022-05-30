package com.rulhouse.evgawatcher.crawler

import android.util.Log
import com.rulhouse.evgawatcher.GpuProduct

object GpuProductsMethods {
    fun getNamedBySerial(gpuProducts: List<GpuProduct>?): List<List<GpuProduct>> {
        var nameList = mutableListOf<String>()
        var gpuProductsNameList = mutableListOf<MutableList<GpuProduct>>()
        gpuProducts?.forEach { product ->
            val pattern = Regex(".* \\d\\d\\d\\d( Ti)?")
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