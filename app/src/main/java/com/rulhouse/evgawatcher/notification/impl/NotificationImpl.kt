package com.rulhouse.evgawatcher.notification.impl

import android.content.Context
import android.util.Log
import com.rulhouse.evgawatcher.data_store.use_cases.NotificationIdDataStoreUseCases
import com.rulhouse.evgawatcher.notification.repository.NotificationRepository
import com.rulhouse.evgawatcher.notification.use_case.NotificationUseCase
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.notification_gpu_product_change.impl.NotificationGpuProductChange
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class NotificationImpl (
    private val context: Context,
    private val notificationIdDataStoreUseCases: NotificationIdDataStoreUseCases
) : NotificationRepository {

    override suspend fun notificationDifferentProducts(differentProducts: List<ProductsDifference>?) {
        coroutineScope {
            notificationIdDataStoreUseCases.getNotificationIdDataStoreFlow().collect {
                val notifyId = it.notificationId
                differentProducts?.forEach { productDifference ->
                    DifferentProductsNotification().doNotify(context, notifyId, productDifference)
                    notificationIdDataStoreUseCases.updateNotificationId(notifyId + 1)
                }
                this@coroutineScope.cancel()
            }
        }
    }
}