package com.rulhouse.evgawatcher.favorite_products.feature_node.di

import android.app.Application
import androidx.room.Room
import com.oucare.bbt_oucare.feature_node.domain.use_case.*
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.data_source.FavoriteGpuProductDataBase
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.repository.FavoriteGpuProductRepository
import com.rulhouse.evgawatcher.favorite_products.feature_node.impl.FavoriteGpuProductImpl
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteGpuProductsModule {
    @Provides
    @Singleton
    fun provideFavoriteGpuProductDatabase(app: Application): FavoriteGpuProductDataBase {
        return Room.databaseBuilder(
            app,
            FavoriteGpuProductDataBase::class.java,
            FavoriteGpuProductDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteGpuProductRepository(db: FavoriteGpuProductDataBase): FavoriteGpuProductRepository {
        return FavoriteGpuProductImpl(db.measurementDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteGpuProductUseCases(repository: FavoriteGpuProductRepository): FavoriteGpuProductUseCases {
        return FavoriteGpuProductUseCases(
            getFavoriteGpuProductsFlow = GetFavoriteGpuProductsFlow(repository),
            getFavoriteGpuProducts = GetFavoriteGpuProducts(repository),
            deleteFavoriteGpuProduct = DeleteFavoriteGpuProduct(repository),
            addFavoriteGpuProduct = AddFavoriteGpuProduct(repository),
            getFavoriteGpuProductFlowByName = GetFavoriteGpuProductFlowByName(repository),
            getFavoriteGpuProductByName = GetFavoriteGpuProductByName(repository),
            getFavoriteGpuProduct = GetFavoriteGpuProduct(repository)
        )
    }
}