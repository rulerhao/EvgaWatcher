package com.rulhouse.evgawatcher.data_store.user_preferences.use_cases

data class UserPreferencesDataStoreUseCases (
    val getUserPreferencesDataStoreFlow: GetUserPreferencesDataStoreFlow,
    val updateShowingOutOfStock: UpdateShowingOutOfStock,
    val updatePriceAscending: UpdatePriceAscending,
    val updateShowingNoPriceProduct: UpdateShowingNoPriceProduct,
    val updateFilterState: UpdateFilterState
)