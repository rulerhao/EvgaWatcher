package com.rulhouse.evgawatcher.methods.data_store.user_preferences.data

import com.rulhouse.evgawatcher.datastore.UserPreferencesProto
import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataStoreRepository {
    fun getUserPreferencesDataStoreFlow(): Flow<UserPreferencesProto>

    suspend fun updateShowingOutOfStock(isOn: Boolean)
    suspend fun updatePriceAscending(isOn: Boolean)
    suspend fun updateShowingNoPrice(isOn: Boolean)
    suspend fun updateFilterState(isOn: Boolean)

    suspend fun fetchInitialPreferences(): UserPreferencesProto
}