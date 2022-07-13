package com.rulhouse.evgawatcher.methods.notification.use_case

import com.rulhouse.evgawatcher.methods.notification.repository.NotificationRepository
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference

class NotificationDifferentProducts(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(productsDifference: List<ProductsDifference>?) {
        return repository.notificationDifferentProducts(productsDifference)
    }
}