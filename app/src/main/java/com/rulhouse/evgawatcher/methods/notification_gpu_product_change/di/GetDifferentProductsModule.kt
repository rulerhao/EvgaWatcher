package com.rulhouse.evgawatcher.methods.notification_gpu_product_change.di

import com.rulhouse.evgawatcher.methods.crawler.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.impl.GetDifferentProductsImpl
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.repository.GetDifferentProductsRepository
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferenceProducts
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
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
    fun provideGetDifferenceProductsRepository(crawlerUseCases: CrawlerUseCases, favoriteGpuProductUseCases: FavoriteGpuProductUseCases): GetDifferentProductsRepository {
        return GetDifferentProductsImpl(crawlerUseCases, favoriteGpuProductUseCases)
    }

    @Provides
    @Singleton
    fun provideGetDifferenceProductsUseCases(repository: GetDifferentProductsRepository): GetDifferentProductsUseCase {
        return GetDifferentProductsUseCase(
            getDifferenceProducts = GetDifferenceProducts(repository),
        )
    }

}