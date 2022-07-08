package com.rulhouse.evgawatcher.presentation.favorite_products_screen.view_model

import androidx.lifecycle.viewModelScope
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.FavoriteGpuProductUseCases
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