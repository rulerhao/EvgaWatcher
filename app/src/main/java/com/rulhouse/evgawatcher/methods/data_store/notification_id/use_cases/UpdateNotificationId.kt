package com.rulhouse.evgawatcher.methods.data_store.notification_id.use_cases

import com.rulhouse.evgawatcher.methods.data_store.notification_id.data.NotificationIdDataStoreRepository

class UpdateNotificationId (
    private val repository: NotificationIdDataStoreRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.updateNotificationId(id)
    }
}