package com.rulhouse.evgawatcher.data_store.user_preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreFactory
import com.rulhouse.evgawatcher.data_store.notification_id.data.NotificationIdDataStoreRepository
import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.GetNotificationIdDataStoreFlow
import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.NotificationIdDataStoreUseCases
import com.rulhouse.evgawatcher.data_store.notification_id.use_cases.UpdateNotificationId
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesDataStoreFactory
import com.rulhouse.evgawatcher.data_store.user_preferences.data.UserPreferencesDataStoreRepository
import com.rulhouse.evgawatcher.data_store.user_preferences.impl.UserPreferencesDataStoreImpl
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.GetUserPreferencesDataStoreFlow
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UpdatePriceAscending
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UpdateShowingOutOfStock
import com.rulhouse.evgawatcher.data_store.user_preferences.use_cases.UserPreferencesDataStoreUseCases
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import com.rulhouse.evgawatcher.datastore.UserPreferencesProto
import com.rulhouse.protobufdatastore.impl.NotificationIdDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserPreferencesDataStoreModule {

    @Singleton
    @Provides
    fun provideUserPreferencesProtoDataStore(@ApplicationContext appContext: Context): DataStore<UserPreferencesProto> {
        return UserPreferencesDataStoreFactory().create(appContext)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesDataStoreRepository(dataStore: DataStore<UserPreferencesProto>): UserPreferencesDataStoreRepository {
        return UserPreferencesDataStoreImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesDataStoreUseCases(repository: UserPreferencesDataStoreRepository): UserPreferencesDataStoreUseCases {
        return UserPreferencesDataStoreUseCases(
            getUserPreferencesDataStoreFlow = GetUserPreferencesDataStoreFlow(repository),
            updateShowingOutOfStock = UpdateShowingOutOfStock(repository),
            updatePriceAscending = UpdatePriceAscending(repository)
        )
    }
}