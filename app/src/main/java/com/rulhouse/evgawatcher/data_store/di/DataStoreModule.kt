package com.rulhouse.evgawatcher.data_store.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import com.rulhouse.evgawatcher.data_store.data.UserPreferencesDataStoreFactory
import com.rulhouse.evgawatcher.data_store.data.NotificationIdDataStoreRepository
import com.rulhouse.protobufdatastore.impl.NotificationIdDataStoreImpl
import com.rulhouse.evgawatcher.data_store.use_cases.GetNotificationIdDataStoreFlow
import com.rulhouse.evgawatcher.data_store.use_cases.UpdateNotificationId
import com.rulhouse.evgawatcher.data_store.use_cases.NotificationIdDataStoreUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideProtoDataStore(@ApplicationContext appContext: Context): DataStore<NotificationIDProto> {
        return UserPreferencesDataStoreFactory.create(appContext)
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