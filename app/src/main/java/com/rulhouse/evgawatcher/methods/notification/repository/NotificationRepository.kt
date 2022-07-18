package com.rulhouse.evgawatcher.methods.notification.repository

import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference

interface NotificationRepository {
    suspend fun notificationDifferentProducts(differentProducts: List<ProductsDifference>?)

    suspend fun notifyProductsChanged(differentProducts: List<ProductsDifference>?)
}