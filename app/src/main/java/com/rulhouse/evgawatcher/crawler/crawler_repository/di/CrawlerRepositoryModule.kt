package com.rulhouse.evgawatcher.crawler.crawler_repository.di

import android.app.Application
import androidx.room.Room
import com.oucare.bbt_oucare.feature_node.domain.use_case.GetFavoriteGpuProduct
import com.rulhouse.evgawatcher.crawler.crawler_repository.data.data_source.CrawlerRepositoryDataBase
import com.rulhouse.evgawatcher.crawler.crawler_repository.domain.repository.CrawlerRepositoryRepository
import com.rulhouse.evgawatcher.crawler.crawler_repository.domain.use_cases.CrawlerRepositoryUseCase
import com.rulhouse.evgawatcher.crawler.crawler_repository.domain.use_cases.GetCrawlerRepositoryFlow
import com.rulhouse.evgawatcher.crawler.crawler_repository.domain.use_cases.InsertCrawlerRepository
import com.rulhouse.evgawatcher.crawler.crawler_repository.impl.CrawlerRepositoryImpl
import com.rulhouse.evgawatcher.crawler.impl.CrawlerImpl
import com.rulhouse.evgawatcher.crawler.repository.CrawlerRepository
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.crawler.use_cases.GetGpuItems
import com.rulhouse.evgawatcher.favorite_products.data.data_source.FavoriteGpuProductDataBase
import com.rulhouse.evgawatcher.favorite_products.domain.repository.FavoriteGpuProductRepository
import com.rulhouse.evgawatcher.favorite_products.domain.use_case.*
import com.rulhouse.evgawatcher.favorite_products.impl.FavoriteGpuProductImpl
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
    fun provideCrawlerRepositoryDatabase(app: Application): CrawlerRepositoryDataBase {
        return Room.databaseBuilder(
            app,
            CrawlerRepositoryDataBase::class.java,
            CrawlerRepositoryDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCrawlerRepositoryRepository(db: CrawlerRepositoryDataBase): CrawlerRepositoryRepository {
        return CrawlerRepositoryImpl(db.crawlerRepositoryDao)
    }

    @Provides
    @Singleton
    fun provideCrawlerRepositoryUseCases(repository: CrawlerRepositoryRepository): CrawlerRepositoryUseCase {
        return CrawlerRepositoryUseCase(
            getCrawlerRepositoryFlow = GetCrawlerRepositoryFlow(repository),
            insertCrawlerRepository = InsertCrawlerRepository(repository),
        )
    }
}