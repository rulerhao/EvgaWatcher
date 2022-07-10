package com.rulhouse.evgawatcher.crawler.crawler_repository.data.data_source

import androidx.room.*
import com.rulhouse.evgawatcher.favorite_products.data.GpuProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CrawlerRepositoryDao {
    @Query("SELECT * FROM GpuProduct")
    suspend fun getProductsFlow(): Flow<List<GpuProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<GpuProduct>)
}