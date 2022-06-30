package com.rulhouse.protobufdatastore.impl

import android.util.Log
import androidx.datastore.core.DataStore
import com.rulhouse.evgawatcher.data_store.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

class NotificationIdDataStoreImpl(
    private val notificationIdDataStore: DataStore<NotificationIDProto>
): NotificationIdDataStoreRepository {

    override fun getNotificationIdDataStoreFlow(): Flow<NotificationIDProto> {
        return notificationIdDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(NotificationIDProto.getDefaultInstance())
                } else {
                    throw exception
                }
            }
    }

    override suspend fun updateNotificationId(id: Int) {
        notificationIdDataStore.updateData { preferences ->
            preferences.toBuilder().setNotificationId(id).build()
        }
    }

    override suspend fun updateShowingOutOfStock(isOn: Boolean) {
        notificationIdDataStore.updateData { preferences ->
            preferences.toBuilder().setShowingOutOfStock(isOn).build()
        }
    }

    override suspend fun updatePriceAscending(isOn: Boolean) {
        notificationIdDataStore.updateData { preferences ->
            preferences.toBuilder().setPriceAscending(isOn).build()
        }
    }

    override suspend fun fetchInitialPreferences() = notificationIdDataStore.data.first()
}