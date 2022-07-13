package com.rulhouse.evgawatcher.get_favorite_products.mock_items

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct

class GetFavoriteProductsMockNullAllProducts {

    val mockProducts: List<GpuProduct>? = null

    val mockFavoriteProducts: List<GpuProduct>? = null

    val expectedResult = emptyList<GpuProduct>()
}