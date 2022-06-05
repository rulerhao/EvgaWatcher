package com.rulhouse.evgawatcher.crawler.di

import android.app.Application
import androidx.room.Room
import com.oucare.bbt_oucare.feature_node.domain.use_case.*
import com.rulhouse.evgawatcher.crawler.feature_node.data.data_source.FavoriteGpuProductDataBase
import com.rulhouse.evgawatcher.crawler.feature_node.domain.repository.FavoriteGpuProductRepository
import com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.ruler.feature_node.data.repository.FavoriteGpuProductRepositoryImpl
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
        return FavoriteGpuProductRepositoryImpl(db.measurementDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteGpuProductUseCases(repository: FavoriteGpuProductRepository): FavoriteGpuProductUseCases {
        return FavoriteGpuProductUseCases(
            getFavoriteGpuProducts = GetFavoriteGpuProducts(repository),
            deleteFavoriteGpuProduct = DeleteFavoriteGpuProduct(repository),
            addFavoriteGpuProduct = AddFavoriteGpuProduct(repository),
            getFavoriteGpuProduct = GetFavoriteGpuProduct(repository)
        )
    }
}