package com.rulhouse.evgawatcher.work_manager.di

import android.content.Context
import com.rulhouse.evgawatcher.work_manager.factory.WorkManagerFactory
import com.rulhouse.evgawatcher.work_manager.impl.WorkManagerImpl
import com.rulhouse.evgawatcher.work_manager.repository.WorkManagerRepository
import com.rulhouse.evgawatcher.work_manager.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {

    @Singleton
    @Provides
    fun provideWorkManagerFactory(): WorkManagerFactory {
        return WorkManagerFactory()
    }

    @Provides
    @Singleton
    fun provideWorkManagerRepository(@ApplicationContext context: Context, workManagerFactory: WorkManagerFactory): WorkManagerRepository {
        return WorkManagerImpl(context, workManagerFactory)
    }

    @Provides
    @Singleton
    fun provideWorkManagerUseCases(repository: WorkManagerRepository): WorkManagerUseCases {
        return WorkManagerUseCases(
            setWork = SetWork(repository),
            setPeriodicWork = SetPeriodicWork(repository),
            cancelPeriodicWork = CancelPeriodicWork(repository),
            setCrawlerWork = SetCrawlerWork(repository),
            cancelCrawlerWork = CancelCrawlerWork(repository),
            getPeriodWorkStateLiveData = GetPeriodWorkState(repository)
        )
    }

}