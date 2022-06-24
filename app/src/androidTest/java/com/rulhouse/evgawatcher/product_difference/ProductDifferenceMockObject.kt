package com.rulhouse.evgawatcher.product_difference

import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference

object ProductDifferenceMockObject {
    val mockPriceGetLower = listOf(
        ProductsDifference(
            gpuProduct = GpuProduct(
                name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
                imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
                serial = "24G-P5-4988-KR",
                canBeBought = true,
                statement = listOf(),
                url = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
                limitedNumber = "",
                price = 10000,
                warranty = "全球保固： 3 年",
                favorite = false
            ),
            reason = DifferenceReason.PriceGetLower
        )
    )

    val mockNull = null
}