package com.rulhouse.evgawatcher.crawler.feature_node.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rulhouse.evgawatcher.GpuProduct
import com.rulhouse.evgawatcher.crawler.feature_node.domain.util.Converters

@Database(
    entities = [GpuProduct::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class FavoriteGpuProductDataBase : RoomDatabase() {

    abstract val measurementDao: FavoriteGpuProductsDao

    companion object {
        const val DATABASE_NAME = "favorite_gpu_product_db"
    }
}