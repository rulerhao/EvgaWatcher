package com.rulhouse.evgawatcher.presentation.favorite_products_screen.view_model

import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.presentation.products_screen.view_model.ProductsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsViewModel @Inject constructor(
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases,
    private val userPreferencesDataStoreUseCases: UserPreferencesDataStoreUseCases,
): ProductsScreenViewModel(
    favoriteGpuProductUseCases,
    userPreferencesDataStoreUseCases
) {

    init {
        viewModelScope.launch {
            favoriteGpuProductUseCases.getFavoriteGpuProductsFlow().collect {
                setProducts(it)
            }
        }
    }

}