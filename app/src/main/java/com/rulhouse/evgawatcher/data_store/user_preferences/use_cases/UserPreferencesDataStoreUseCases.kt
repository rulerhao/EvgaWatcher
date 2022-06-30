package com.rulhouse.evgawatcher.data_store.user_preferences.use_cases

import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.GetNotificationIdDataStoreFlow
import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.UpdateNotificationId

data class UserPreferencesDataStoreUseCases (
    val getUserPreferencesDataStoreFlow: GetUserPreferencesDataStoreFlow,
    val updateShowingOutOfStock: UpdateShowingOutOfStock,
    val updatePriceAscending: UpdatePriceAscending,
)