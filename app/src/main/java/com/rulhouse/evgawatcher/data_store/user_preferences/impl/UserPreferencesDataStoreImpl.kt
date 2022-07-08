package com.rulhouse.evgawatcher.data_store.user_preferences.impl

import androidx.datastore.core.DataStore
import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesDataStoreRepository
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import com.rulhouse.evgawatcher.datastore.UserPreferencesProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

class UserPreferencesDataStoreImpl(
    private val userPreferencesDataStore: DataStore<UserPreferencesProto>
): UserPreferencesDataStoreRepository {

    override fun getUserPreferencesDataStoreFlow(): Flow<UserPreferencesProto> {
        return userPreferencesDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(UserPreferencesProto.getDefaultInstance())
                } else {
                    throw exception
                }
            }
    }

    override suspend fun updateShowingOutOfStock(isOn: Boolean) {
        userPreferencesDataStore.updateData { preferences ->
            preferences.toBuilder().setShowingOutOfStock(isOn).build()
        }
    }

    override suspend fun updatePriceAscending(isOn: Boolean) {
        userPreferencesDataStore.updateData { preferences ->
            preferences.toBuilder().setPriceAscending(isOn).build()
        }
    }

    override suspend fun updateShowingNoPrice(isOn: Boolean) {
        userPreferencesDataStore.updateData { preferences ->
            preferences.toBuilder().setShowingNoPrice(isOn).build()
        }
    }

    override suspend fun updateFilterState(isOn: Boolean) {
        userPreferencesDataStore.updateData { preferences ->
            preferences.toBuilder().setFilterState(isOn).build()
        }
    }

    override suspend fun fetchInitialPreferences() = userPreferencesDataStore.data.first()
}