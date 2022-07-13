package com.rulhouse.evgawatcher.get_favorite_products

import com.rulhouse.evgawatcher.methods.crawler.crawler.util.GpuProductsMethods
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNormal
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNullAllProducts
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNullFavoriteProducts
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNullProducts
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class GetFavoriteProductsTest {

    @Test
    fun testAllNullProducts() {
        val mockProducts = GetFavoriteProductsMockNullAllProducts()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getFavoriteProducts(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }

    @Test
    fun testNullFavoriteProducts() {
        val mockProducts = GetFavoriteProductsMockNullFavoriteProducts()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getFavoriteProducts(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }

    @Test
    fun testNullProducts() {
        val mockProducts = GetFavoriteProductsMockNullProducts()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getFavoriteProducts(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }

    @Test
    fun testNormalProducts() {
        val mockProducts = GetFavoriteProductsMockNormal()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getFavoriteProducts(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }
}