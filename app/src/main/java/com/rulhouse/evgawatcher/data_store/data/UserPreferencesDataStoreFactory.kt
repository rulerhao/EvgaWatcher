package com.rulhouse.evgawatcher.data_store.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class UserPreferencesDataStoreFactory {
    companion object {
        private const val NOTIFICATION_NAME = "user_preferences"
        private const val NOTIFICATION_DATA_STORE_FILE_NAME = "user_prefs.pb"

        fun create(context: Context): DataStore<NotificationIDProto> {
            return DataStoreFactory.create(
                serializer = NotificationIdDataStoreSerializer,
                produceFile = { context.dataStoreFile(NOTIFICATION_DATA_STORE_FILE_NAME) },
                corruptionHandler = null,
                migrations = listOf(
                    SharedPreferencesMigration(
                        context = context,
                        sharedPreferencesName = NOTIFICATION_NAME
                    ) { sharedPrefs: SharedPreferencesView, currentData: NotificationIDProto ->
                        // Define the mapping from SharedPreferences to UserPreferences
                        currentData.toBuilder().notificationId = sharedPrefs.getInt("notification_id", 1)
                        currentData
                    }
                ),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
            )
        }
    }
}