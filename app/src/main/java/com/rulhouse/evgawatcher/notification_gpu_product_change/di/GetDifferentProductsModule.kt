package com.rulhouse.evgawatcher.notification_gpu_product_change.di

import com.rulhouse.evgawatcher.notification_gpu_product_change.impl.GetDifferentProductsImpl
import com.rulhouse.evgawatcher.notification_gpu_product_change.repository.GetDifferentProductsRepository
import com.rulhouse.evgawatcher.notification_gpu_product_change.use_case.GetDifferenceProducts
import com.rulhouse.evgawatcher.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetDifferentProductsModule {

    @Provides
    @Singleton
    fun provideGetDifferenceProductsRepository(): GetDifferentProductsRepository {
        return GetDifferentProductsImpl()
    }

    @Provides
    @Singleton
    fun provideGetDifferenceProductsUseCases(repository: GetDifferentProductsRepository): GetDifferentProductsUseCase {
        return GetDifferentProductsUseCase(
            getDifferenceProducts = GetDifferenceProducts(repository),
        )
    }

}