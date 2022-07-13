package com.rulhouse.evgawatcher.methods.data_store.user_preferences.use_cases

import com.rulhouse.evgawatcher.methods.data_store.user_preferences.data.UserPreferencesDataStoreRepository

class UpdatePriceAscending (
    private val repository: UserPreferencesDataStoreRepository
) {
    suspend operator fun invoke(isOn: Boolean) {
        return repository.updatePriceAscending(isOn)
    }
}