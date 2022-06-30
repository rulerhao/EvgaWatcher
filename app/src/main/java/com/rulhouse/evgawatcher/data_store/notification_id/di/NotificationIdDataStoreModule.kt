package com.rulhouse.evgawatcher.data_store.notification_id.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreFactory
import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.GetNotificationIdDataStoreFlow
import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.NotificationIdDataStoreUseCases
import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.UpdateNotificationId
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import com.rulhouse.protobufdatastore.impl.NotificationIdDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationIdDataStoreModule {

    @Singleton
    @Provides
    fun provideProtoDataStore(@ApplicationContext appContext: Context): DataStore<NotificationIDProto> {
        return NotificationIdDataStoreFactory().create(appContext)
    }

    @Provides
    @Singleton
    fun provideNotificationIdDataStoreRepository(dataStore: DataStore<NotificationIDProto>): NotificationIdDataStoreRepository {
        return NotificationIdDataStoreImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideNotificationIdDataStoreUseCases(repository: NotificationIdDataStoreRepository): NotificationIdDataStoreUseCases {
        return NotificationIdDataStoreUseCases(
            getNotificationIdDataStoreFlow = GetNotificationIdDataStoreFlow(repository),
            updateNotificationId = UpdateNotificationId(repository)
        )
    }
}