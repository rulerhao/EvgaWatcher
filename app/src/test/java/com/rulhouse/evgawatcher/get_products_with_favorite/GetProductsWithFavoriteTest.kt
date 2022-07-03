package com.rulhouse.evgawatcher.get_products_with_favorite

import com.rulhouse.evgawatcher.crawler.GpuProductsMethods
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNormal
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNullAllProducts
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNullFavoriteProducts
import com.rulhouse.evgawatcher.get_favorite_products.mock_items.GetFavoriteProductsMockNullProducts
import com.rulhouse.evgawatcher.get_products_with_favorite.mock_items.GetProductsWithFavoriteMockNullAllProducts
import com.rulhouse.evgawatcher.get_products_with_favorite.mock_items.GetProductsWithFavoriteMockNullFavoriteProducts
import com.rulhouse.evgawatcher.get_products_with_favorite.mock_items.GetProductsWithFavoriteMockNullProducts
import com.rulhouse.evgawatcher.get_products_with_favorite.mock_items.GetProductsWithFavoritesMockNormal
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class GetProductsWithFavoriteTest {

    @Test
    fun testAllNullProducts() {
        val mockProducts = GetProductsWithFavoriteMockNullAllProducts()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getProductsWithFavorites(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }

    @Test
    fun testNullFavoriteProducts() {
        val mockProducts = GetProductsWithFavoriteMockNullFavoriteProducts()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getProductsWithFavorites(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }

    @Test
    fun testNullProducts() {
        val mockProducts = GetProductsWithFavoriteMockNullProducts()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getProductsWithFavorites(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }

    @Test
    fun testNormalProducts() {
        val mockProducts = GetProductsWithFavoritesMockNormal()
        val gpuProductsMethods = GpuProductsMethods
        val ans = gpuProductsMethods.getProductsWithFavorites(mockProducts.mockProducts, mockProducts.mockFavoriteProducts)

        assertEquals(mockProducts.expectedResult, ans)
    }
}