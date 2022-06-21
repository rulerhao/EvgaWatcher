package com.rulhouse.evgawatcher

import com.rulhouse.evgawatcher.get_different_products.buyable.TestBecomeBuyableObject
import com.rulhouse.evgawatcher.get_different_products.buyable.TestBecomeNotBuyableObject
import com.rulhouse.evgawatcher.get_different_products.price.TestPriceGoesDownObject
import com.rulhouse.evgawatcher.get_different_products.price.TestPriceGoesUpObject
import com.rulhouse.evgawatcher.notification_gpu_product_change.impl.GetGpuProductsDifference
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GpuProductDifferenceTest {

    @Before
    fun setup() {

    }

    @Test
    fun testPriceGoesUp() {
        val mockItems = TestPriceGoesUpObject()
        val result = GetGpuProductsDifference().getDifference(mockItems.favoriteGpuProduct, mockItems.crawlerGpuProduct)

        assertEquals(mockItems.resultGpuProduct, result)
    }

    @Test
    fun testPriceGoesDown() {
        val mockItems = TestPriceGoesDownObject()
        val result = GetGpuProductsDifference().getDifference(mockItems.favoriteGpuProduct, mockItems.crawlerGpuProduct)

        assertEquals(mockItems.resultGpuProduct, result)
    }

    @Test
    fun testBecomeBuyable() {
        val mockItems = TestBecomeBuyableObject()
        val result = GetGpuProductsDifference().getDifference(mockItems.favoriteGpuProduct, mockItems.crawlerGpuProduct)

        assertEquals(mockItems.resultGpuProduct, result)
    }

    @Test
    fun testBecomeNotBuyable() {
        val mockItems = TestBecomeNotBuyableObject()
        val result = GetGpuProductsDifference().getDifference(mockItems.favoriteGpuProduct, mockItems.crawlerGpuProduct)

        assertEquals(mockItems.resultGpuProduct, result)
    }

}