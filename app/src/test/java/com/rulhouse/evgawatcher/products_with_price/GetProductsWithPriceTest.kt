package com.rulhouse.evgawatcher.products_with_price

import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.products_with_price.mock_items.GetProductsExcludingNoPriceTestMock
import com.rulhouse.evgawatcher.products_with_price.mock_items.GetProductsIncludingNoPriceTestMock
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class GetProductsWithPriceTest {

    @Test
    fun testProductsExcludingNoPrice() {
        val mockProducts = GetProductsExcludingNoPriceTestMock()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getWithPriceProducts(mockProducts.mockProducts, excludingNoPrice = true)

        assertEquals(mockProducts.expectedResult, ans)
    }

    @Test
    fun testProductsIncludingNoPrice() {
        val mockProducts = GetProductsIncludingNoPriceTestMock()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getWithPriceProducts(mockProducts.mockProducts, excludingNoPrice = false)

        assertEquals(mockProducts.expectedResult, ans)
    }
}