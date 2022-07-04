package com.rulhouse.evgawatcher.data_store.user_preferences.data

data class UserPreferencesState(
    val showingOutOfStock: Boolean = false,
    val priceAscending: Boolean = false,
    val showingNoPrice: Boolean = false
)
