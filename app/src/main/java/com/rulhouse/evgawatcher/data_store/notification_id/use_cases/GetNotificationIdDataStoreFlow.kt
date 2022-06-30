package com.rulhouse.evgawatcher.data_store.notification_id.use_cases

import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import kotlinx.coroutines.flow.Flow

class GetNotificationIdDataStoreFlow (
    private val repository: NotificationIdDataStoreRepository
) {
    operator fun invoke(): Flow<NotificationIDProto> {
        return repository.getNotificationIdDataStoreFlow()
    }
}