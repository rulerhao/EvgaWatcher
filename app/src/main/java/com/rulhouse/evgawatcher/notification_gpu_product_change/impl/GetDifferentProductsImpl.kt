package com.rulhouse.evgawatcher.notification_gpu_product_change.impl

import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.notification_gpu_product_change.repository.GetDifferentProductsRepository
import javax.inject.Inject

class GetDifferentProductsImpl: GetDifferentProductsRepository {
    @Inject
    lateinit var notificationGpuProductChange: NotificationGpuProductChange

    override suspend fun getDifference(): List<ProductsDifference> {
        return notificationGpuProductChange.getDifferenceProducts()
    }
}