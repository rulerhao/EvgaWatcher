package com.rulhouse.evgawatcher.data_store.user_preferences.use_cases

import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesDataStoreRepository
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import com.rulhouse.evgawatcher.datastore.UserPreferencesProto
import kotlinx.coroutines.flow.Flow

class GetUserPreferencesDataStoreFlow (
    private val repository: UserPreferencesDataStoreRepository
) {
    operator fun invoke(): Flow<UserPreferencesProto> {
        return repository.getUserPreferencesDataStoreFlow()
    }
}