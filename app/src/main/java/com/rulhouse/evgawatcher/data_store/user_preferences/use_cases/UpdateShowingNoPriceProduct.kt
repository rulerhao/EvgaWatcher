package com.rulhouse.evgawatcher.data_store.user_preferences.use_cases

import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesDataStoreRepository

class UpdateShowingNoPriceProduct (
    private val repository: UserPreferencesDataStoreRepository
) {
    suspend operator fun invoke(isOn: Boolean) {
        return repository.updateShowingNoPrice(isOn)
    }
}