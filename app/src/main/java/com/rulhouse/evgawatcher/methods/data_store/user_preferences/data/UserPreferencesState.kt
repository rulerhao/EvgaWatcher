package com.rulhouse.evgawatcher.methods.data_store.user_preferences.data

data class UserPreferencesState(
    val showingOutOfStock: Boolean = false,
    val priceAscending: Boolean = false,
    val showingNoPrice: Boolean = false,
    val filterState: Boolean = false
)
