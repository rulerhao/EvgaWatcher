package com.rulhouse.evgawatcher.presentation.favorite_products_screen

import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.products_screen.ExpandCollapseModel

object ViewModelMethods {
    fun getModels(
        productsSortedBySerial: List<List<GpuProduct>>?,
        newProductsSortedBySerial: List<List<GpuProduct>>,
        models: List<ExpandCollapseModel>
    ): List<ExpandCollapseModel> {
        if (productsSortedBySerial != null) {
            if (productsSortedBySerial.isEmpty()) {
                return getInitModels(newProductsSortedBySerial)
            } else {
                if (productsSortedBySerial.size > newProductsSortedBySerial.size) {
                    for (i in productsSortedBySerial.indices) {
                        if (i < productsSortedBySerial.size - 1) {
                            val productName = GpuProductsMethods.getNameBySerial(productsSortedBySerial[i][0].name)
                            val newProductsName =
                                GpuProductsMethods.getNameBySerial(newProductsSortedBySerial[i][0].name)
                            if (productName != newProductsName) {
                                val newProductsSortedBySerialModel =
                                    models.toMutableList()
                                newProductsSortedBySerialModel.removeAt(i)
                                return newProductsSortedBySerialModel
                            }
                        }
                        else if (i == productsSortedBySerial.size - 1) {
                            val newProductsSortedBySerialModel =
                                models.toMutableList()
                            newProductsSortedBySerialModel.removeAt(i)
                            return newProductsSortedBySerialModel
                        }
                    }
                } else if (productsSortedBySerial.size < newProductsSortedBySerial.size) {
                    for (i in newProductsSortedBySerial.indices) {
                        if (i <= productsSortedBySerial.size - 1) {
                            val productName = GpuProductsMethods.getNameBySerial(productsSortedBySerial[i][0].name)
                            val newProductsName =
                                GpuProductsMethods.getNameBySerial(newProductsSortedBySerial[i][0].name)
                            if (productName != newProductsName) {
                                val newProductsSortedBySerialModel =
                                    models.toMutableList()
                                newProductsSortedBySerialModel.add(
                                    i,
                                    ExpandCollapseModel(
                                        title = GpuProductsMethods.getNameBySerial(newProductsSortedBySerial[i][0].name)!!,
                                        isOpen = false
                                    )
                                )
                                return newProductsSortedBySerialModel
                            }
                        }
                        else if (i == newProductsSortedBySerial.size - 1) {
                            val newProductsSortedBySerialModel =
                                models.toMutableList()
                            newProductsSortedBySerialModel.add(
                                newProductsSortedBySerial.size - 1,
                                ExpandCollapseModel(
                                    title = GpuProductsMethods.getNameBySerial(newProductsSortedBySerial[newProductsSortedBySerial.size - 1][0].name)!!,
                                    isOpen = false
                                )
                            )
                            return newProductsSortedBySerialModel
                        }
                    }
                }
            }
        }
        return models
    }

    private fun getInitModels(productsSortedBySerial: List<List<GpuProduct>>?): List<ExpandCollapseModel> {
        val models: MutableList<ExpandCollapseModel> = mutableListOf()
        productsSortedBySerial?.forEach { item ->
            models.add(
                ExpandCollapseModel(
                    title = GpuProductsMethods.getNameBySerial(item[0].name)!!,
                    isOpen = false
                )
            )
        }
        return models
    }
}