package com.rulhouse.evgawatcher.data_store.user_preferences.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import com.rulhouse.evgawatcher.datastore.UserPreferences
import com.rulhouse.evgawatcher.datastore.UserPreferencesProto
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesDataStoreSerializer : Serializer<UserPreferencesProto> {
    override val defaultValue: UserPreferencesProto = UserPreferencesProto.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): UserPreferencesProto {
        try {
            return UserPreferencesProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserPreferencesProto, output: OutputStream) = t.writeTo(output)
}