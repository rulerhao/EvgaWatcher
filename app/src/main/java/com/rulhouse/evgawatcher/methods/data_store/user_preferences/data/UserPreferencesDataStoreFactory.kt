package com.rulhouse.evgawatcher.methods.data_store.user_preferences.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView
import com.rulhouse.evgawatcher.datastore.UserPreferencesProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class UserPreferencesDataStoreFactory {
    private val SHARED_PREFERENCES_NAME = "user_preferences"

    private val USER_PREFERENCES_FILE_NAME = "user_preferences_data_store.pb"

    fun create(context: Context): DataStore<UserPreferencesProto> {
        return DataStoreFactory.create(
            serializer = UserPreferencesDataStoreSerializer,
            produceFile = { context.dataStoreFile(USER_PREFERENCES_FILE_NAME) },
            corruptionHandler = null,
            migrations = listOf(
                SharedPreferencesMigration(
                    context = context,
                    sharedPreferencesName = SHARED_PREFERENCES_NAME
                ) { sharedPrefs: SharedPreferencesView, currentData: UserPreferencesProto ->
                    // Define the mapping from SharedPreferences to UserPreferences
                    currentData.toBuilder().also {
                        it.showingOutOfStock = sharedPrefs.getBoolean("showing_out_of_stock", false)
                        it.priceAscending = sharedPrefs.getBoolean("price_ascending", false)
                        it.showingNoPrice = sharedPrefs.getBoolean("showing_no_price", false)
                        it.filterState = sharedPrefs.getBoolean("filter_state", false)
                    }
                    currentData
                }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }
}