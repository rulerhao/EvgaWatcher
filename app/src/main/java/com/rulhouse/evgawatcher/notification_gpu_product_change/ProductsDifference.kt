package com.rulhouse.evgawatcher.notification_gpu_product_change

import android.os.Parcelable
import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsDifference(
    val gpuProduct: GpuProduct,
    val reason: DifferenceReason
) : Parcelable