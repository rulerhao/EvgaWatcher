package com.rulhouse.evgawatcher.data_store.notification_id.data

import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import kotlinx.coroutines.flow.Flow

interface NotificationIdDataStoreRepository {
    fun getNotificationIdDataStoreFlow(): Flow<NotificationIDProto>

    suspend fun updateNotificationId(id: Int)

    suspend fun fetchInitialPreferences(): NotificationIDProto
}