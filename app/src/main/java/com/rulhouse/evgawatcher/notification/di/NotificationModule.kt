package com.rulhouse.evgawatcher.notification.di

import android.content.Context
import com.rulhouse.evgawatcher.data_store.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.data_store.use_cases.GetNotificationIdDataStoreFlow
import com.rulhouse.evgawatcher.data_store.use_cases.NotificationIdDataStoreUseCases
import com.rulhouse.evgawatcher.data_store.use_cases.UpdateNotificationId
import com.rulhouse.evgawatcher.notification.impl.NotificationImpl
import com.rulhouse.evgawatcher.notification.repository.NotificationRepository
import com.rulhouse.evgawatcher.notification.use_case.NotificationDifferentProducts
import com.rulhouse.evgawatcher.notification.use_case.NotificationUseCase
import com.rulhouse.evgawatcher.notification_gpu_product_change.impl.GetDifferentProductsImpl
import com.rulhouse.evgawatcher.notification_gpu_product_change.repository.GetDifferentProductsRepository
import com.rulhouse.evgawatcher.notification_gpu_product_change.use_case.GetDifferenceProducts
import com.rulhouse.evgawatcher.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideNotificationRepository(@ApplicationContext context: Context, useCase: NotificationIdDataStoreUseCases): NotificationRepository {
        return NotificationImpl(context, useCase)
    }

    @Provides
    @Singleton
    fun provideNotificationUseCases(repository: NotificationRepository): NotificationUseCase {
        return NotificationUseCase(
            notificationDifferentProducts = NotificationDifferentProducts(repository),
        )
    }

}