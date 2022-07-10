package com.rulhouse.evgawatcher.get_different_products.price

import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference

class TestPriceGoesDownObject {
    val favoriteGpuProduct: List<GpuProduct> = listOf(
        GpuProduct(
            name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
            imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
            serial = "24G-P5-4988-KR",
            canBeBought = true,
            statement = listOf(),
            uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
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
            canBeBought = true,
            statement = listOf(),
            uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
            limitedNumber = "",
            price = 10000,
            warranty = "全球保固： 3 年",
            favorite = false
        )
    )

    val resultGpuProduct: List<ProductsDifference> = listOf(
        ProductsDifference(
            gpuProduct = GpuProduct(
                name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
                imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
                serial = "24G-P5-4988-KR",
                canBeBought = true,
                statement = listOf(),
                uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
                limitedNumber = "",
                price = 10000,
                warranty = "全球保固： 3 年",
                favorite = false
            ),
            reason = DifferenceReason.PriceGetLower
        )
    )
}