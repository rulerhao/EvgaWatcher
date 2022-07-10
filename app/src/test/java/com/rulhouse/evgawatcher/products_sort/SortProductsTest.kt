package com.rulhouse.evgawatcher.products_sort

import com.rulhouse.evgawatcher.methods.crawler.crawler.util.GpuProductsMethods
import com.rulhouse.evgawatcher.products_sort.mock_products.*
import junit.framework.Assert.assertEquals
import org.junit.Test

class SortProductsTest {

    @Test
    fun testNotShowUnbuyableAndPriceAscending() {
        val mockProducts = NotShowUnbuyableAndPriceAscendingMock()
        val sortedMethods = GpuProductsMethods
        val ans = GpuProductsMethods.sortProductsWithPrice(mockProducts.mock1, showingOutOfStock = false, priceAscending = true)

        assertEquals(mockProducts.mock1Ans, ans)
    }

    @Test
    fun testNotShowUnbuyableAndPriceDescending() {
        val mockProducts = NotShowUnbuyableAndPriceDescendingMock()
        val sortedMethods = GpuProductsMethods
        val ans = GpuProductsMethods.sortProductsWithPrice(mockProducts.mock1, showingOutOfStock = false, priceAscending = false)

        assertEquals(mockProducts.mock1Ans, ans)
    }

    @Test
    fun testShowUnbuyableAndPriceAscending() {
        val mockProducts = ShowUnbuyableAndPriceAscendingMock()
        val sortedMethods = GpuProductsMethods
        val ans = GpuProductsMethods.sortProductsWithPrice(mockProducts.mock1, showingOutOfStock = true, priceAscending = true)

        assertEquals(mockProducts.mock1Ans, ans)
    }

    @Test
    fun testShowUnbuyableAndPriceDescendingMock() {
        val mockProducts = ShowUnbuyableAndPriceDescendingMock()
        val sortedMethods = GpuProductsMethods
        val ans = GpuProductsMethods.sortProductsWithPrice(mockProducts.mock1, showingOutOfStock = true, priceAscending = false)

        assertEquals(mockProducts.mock1Ans, ans)
    }

}