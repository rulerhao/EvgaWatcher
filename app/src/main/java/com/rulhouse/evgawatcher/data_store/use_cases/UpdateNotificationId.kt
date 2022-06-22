package com.rulhouse.evgawatcher.data_store.use_cases

import com.rulhouse.evgawatcher.data_store.data.NotificationIdDataStoreRepository

class UpdateNotificationId (
    private val repository: NotificationIdDataStoreRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.updateNotificationId(id)
    }
}