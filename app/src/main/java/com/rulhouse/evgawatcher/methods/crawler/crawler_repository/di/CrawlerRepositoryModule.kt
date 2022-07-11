package com.rulhouse.evgawatcher.methods.crawler.crawler_repository.di

import android.app.Application
import androidx.room.Room
import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.data.data_source.CrawlerRepositoryDataBase
import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.repository.CrawlerRepositoryRepository
import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.use_cases.*
import com.rulhouse.evgawatcher.methods.crawler.crawler_repository.impl.CrawlerRepositoryImpl
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
            insertCrawlerRepositoryProduct = InsertCrawlerRepositoryProduct(repository),
            getCrawlerRepositoryProductByName = GetCrawlerRepositoryProductByName(repository)
        )
    }
}