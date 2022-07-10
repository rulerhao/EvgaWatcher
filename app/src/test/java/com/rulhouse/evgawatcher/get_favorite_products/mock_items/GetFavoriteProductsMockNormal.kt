package com.rulhouse.evgawatcher.get_favorite_products.mock_items

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct

internal class GetFavoriteProductsMockNormal {

    val mockProducts = listOf(
        GpuProduct(
            name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
            imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
            serial = "24G-P5-4988-KR",
            canBeBought = false,
            statement = listOf(),
            uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
            limitedNumber = "",
            price = 20000,
            warranty = "全球保固： 3 年",
            favorite = false
        ),
        GpuProduct(
            name = "EVGA GeForce RTX 3080 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
            imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
            serial = "24G-P5-4988-KR",
            canBeBought = false,
            statement = listOf(),
            uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
            limitedNumber = "",
            price = 20000,
            warranty = "全球保固： 3 年",
            favorite = false
        )
    )

    val mockFavoriteProducts = listOf(
        GpuProduct(
            name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
            imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
            serial = "24G-P5-4988-KR",
            canBeBought = false,
            statement = listOf(),
            uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
            limitedNumber = "",
            price = 20000,
            warranty = "全球保固： 3 年",
            favorite = true
        )
    )

    val expectedResult = listOf(
        GpuProduct(
            name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
            imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
            serial = "24G-P5-4988-KR",
            canBeBought = false,
            statement = listOf(),
            uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
            limitedNumber = "",
            price = 20000,
            warranty = "全球保固： 3 年",
            favorite = true
        )
    )
}