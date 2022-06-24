package com.rulhouse.evgawatcher.notification.repository

import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference

interface NotificationRepository {
    suspend fun notificationDifferentProducts(differentProducts: List<ProductsDifference>?)
}