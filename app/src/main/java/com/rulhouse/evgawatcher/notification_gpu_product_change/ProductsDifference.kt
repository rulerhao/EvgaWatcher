package com.rulhouse.evgawatcher.notification_gpu_product_change

import android.os.Parcelable
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class ProductsDifference(
    val gpuProduct: GpuProduct,
    val reason: DifferenceReason
) : Parcelable