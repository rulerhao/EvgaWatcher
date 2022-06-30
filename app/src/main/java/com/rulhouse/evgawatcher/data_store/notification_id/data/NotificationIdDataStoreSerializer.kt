package com.rulhouse.evgawatcher.data_store.notification_id.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.rulhouse.evgawatcher.datastore.NotificationIDProto
import java.io.InputStream
import java.io.OutputStream

object NotificationIdDataStoreSerializer : Serializer<NotificationIDProto> {
    override val defaultValue: NotificationIDProto = NotificationIDProto.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): NotificationIDProto {
        try {
            return NotificationIDProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: NotificationIDProto, output: OutputStream) = t.writeTo(output)
}