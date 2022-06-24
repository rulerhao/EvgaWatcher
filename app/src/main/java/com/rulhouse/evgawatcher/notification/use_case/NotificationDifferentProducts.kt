package com.rulhouse.evgawatcher.notification.use_case

import com.rulhouse.evgawatcher.notification.repository.NotificationRepository
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.notification_gpu_product_change.repository.GetDifferentProductsRepository

class NotificationDifferentProducts(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(productsDifference: List<ProductsDifference>?) {
        return repository.notificationDifferentProducts(productsDifference)
    }
}