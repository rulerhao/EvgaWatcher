package com.rulhouse.evgawatcher.data_store.use_cases

import com.rulhouse.evgawatcher.data_store.data.NotificationIdDataStoreRepository

class UpdateShowingOutOfStock (
    private val repository: NotificationIdDataStoreRepository
) {
    suspend operator fun invoke(isOn: Boolean) {
        return repository.updateShowingOutOfStock(isOn)
    }
}