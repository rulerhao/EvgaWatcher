package com.rulhouse.evgawatcher.data_store.use_cases

import com.rulhouse.evgawatcher.data_store.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import kotlinx.coroutines.flow.Flow

class GetNotificationIdDataStoreFlow (
    private val repository: NotificationIdDataStoreRepository
) {
    suspend operator fun invoke(): Flow<NotificationIDProto> {
        return repository.getNotificationIdDataStoreFlow()
    }
}