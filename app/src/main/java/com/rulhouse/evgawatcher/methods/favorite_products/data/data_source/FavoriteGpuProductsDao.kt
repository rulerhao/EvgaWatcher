package com.rulhouse.evgawatcher.methods.favorite_products.data.data_source

import androidx.room.*
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGpuProductsDao {

    @Query("SELECT * FROM GpuProduct")
    fun getFavoriteGpuProductsFlow(): Flow<List<GpuProduct>>

    @Query("SELECT * FROM GpuProduct")
    fun getFavoriteGpuProducts(): List<GpuProduct>

    @Query("SELECT * FROM GpuProduct WHERE canBeBought = :canBeBrought")
    fun getFavoriteGpuProductsCanBeBrought(canBeBrought: Boolean): Flow<List<GpuProduct>>

    @Query("SELECT * FROM GpuProduct WHERE name = :name")
    suspend fun getFavoriteGpuProductByName(name: String): GpuProduct?

    @Query("SELECT * FROM GpuProduct WHERE name = :name")
    fun getFavoriteGpuProductFlowByName(name: String): Flow<GpuProduct>

    @Query("SELECT * FROM GpuProduct WHERE id = :id")
    suspend fun getFavoriteGpuProductById(id: Int) : GpuProduct?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGpuProduct(note: GpuProduct)

    @Delete
    suspend fun deleteFavoriteGpuProduct(note: GpuProduct)
}