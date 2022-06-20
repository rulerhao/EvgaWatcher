package com.rulhouse.evgawatcher.get_different_products.buyable

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct

class TestBecomeNotBuyableObject {
    val favoriteGpuProduct: List<GpuProduct> = listOf(
        GpuProduct(
            name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
            imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
            serial = "24G-P5-4988-KR",
            canBeBought = true,
            statement = listOf(),
            url = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
            limitedNumber = "",
            price = 20000,
            warranty = "全球保固： 3 年",
            favorite = true
        )
    )

    val crawlerGpuProduct: List<GpuProduct> = listOf(
        GpuProduct(
            name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
            imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
            serial = "24G-P5-4988-KR",
            canBeBought = false,
            statement = listOf(),
            url = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
            limitedNumber = "",
            price = 10000,
            warranty = "全球保固： 3 年",
            favorite = false
        )
    )
}