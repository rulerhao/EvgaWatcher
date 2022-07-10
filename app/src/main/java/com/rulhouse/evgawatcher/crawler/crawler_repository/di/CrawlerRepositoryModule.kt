package com.rulhouse.evgawatcher.crawler.crawler_repository.di

import com.rulhouse.evgawatcher.crawler.impl.CrawlerImpl
import com.rulhouse.evgawatcher.crawler.repository.CrawlerRepository
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.crawler.use_cases.GetGpuItems
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CrawlerRepositoryModule {
    @Provides
    @Singleton
    fun provideMeasurementRepository(): CrawlerRepository {
        return CrawlerImpl()
    }

    @Provides
    @Singleton
    fun provideMeasurementUseCases(repository: CrawlerRepository): CrawlerUseCases {
        return CrawlerUseCases(
            getGpuItems = GetGpuItems(repository),
        )
    }
}