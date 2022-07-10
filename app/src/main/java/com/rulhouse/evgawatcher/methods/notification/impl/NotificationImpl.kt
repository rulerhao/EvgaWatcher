package com.rulhouse.evgawatcher.methods.notification.impl

import android.content.Context
import com.rulhouse.evgawatcher.methods.data_store.notification_id.use_cases.NotificationIdDataStoreUseCases
import com.rulhouse.evgawatcher.methods.notification.repository.NotificationRepository
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope

class NotificationImpl (
    private val context: Context,
    private val notificationIdDataStoreUseCases: NotificationIdDataStoreUseCases
) : NotificationRepository {

    override suspend fun notificationDifferentProducts(differentProducts: List<ProductsDifference>?) {
        if (differentProducts == null) {
            return
        }
        if (differentProducts.isEmpty()) {
            return
        }
        coroutineScope {
            notificationIdDataStoreUseCases.getNotificationIdDataStoreFlow().collect {
                val notifyId = it.notificationId
                differentProducts.forEach { productDifference ->
                    DifferentProductsNotification().doNotifyProductsDifference(context, notifyId, productDifference)
                    notificationIdDataStoreUseCases.updateNotificationId(notifyId + 1)
                }
                this@coroutineScope.cancel()
            }
        }
    }
}