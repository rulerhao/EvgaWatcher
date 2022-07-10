package com.rulhouse.evgawatcher.get_products_with_favorite.mock_items

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct

class GetProductsWithFavoriteMockNullAllProducts {

    val mockProducts: List<GpuProduct>? = null

    val mockFavoriteProducts: List<GpuProduct>? = null

    val expectedResult = emptyList<GpuProduct>()
}