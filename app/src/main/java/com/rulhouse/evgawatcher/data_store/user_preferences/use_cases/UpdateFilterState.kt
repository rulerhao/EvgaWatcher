package com.rulhouse.evgawatcher.data_store.user_preferences.use_cases

import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesDataStoreRepository

class UpdateFilterState (
    private val repository: UserPreferencesDataStoreRepository
) {
    suspend operator fun invoke(isOn: Boolean) {
        return repository.updateFilterState(isOn)
    }
}