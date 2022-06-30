package com.rulhouse.evgawatcher.data_store.use_cases

import com.rulhouse.evgawatcher.data_store.data.NotificationIdDataStoreRepository

class UpdatePriceAscending (
    private val repository: NotificationIdDataStoreRepository
) {
    suspend operator fun invoke(isOn: Boolean) {
        return repository.updatePriceAscending(isOn)
    }
}