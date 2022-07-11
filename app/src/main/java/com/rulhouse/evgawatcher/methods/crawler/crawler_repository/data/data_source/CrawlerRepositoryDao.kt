package com.rulhouse.evgawatcher.methods.crawler.crawler_repository.data.data_source

import androidx.room.*
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CrawlerRepositoryDao {
    @Query("SELECT * FROM GpuProduct")
    fun getProductsFlow(): Flow<List<GpuProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<GpuProduct>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: GpuProduct)

    @Query("SELECT * FROM GpuProduct WHERE name = :name")
    suspend fun getProductByName(name: String): GpuProduct?
}