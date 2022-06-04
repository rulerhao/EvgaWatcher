package com.rulhouse.evgawatcher

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GpuProduct(
    val serial: String,
    val name: String,
    val statement: List<String?>,
    val warranty: String,
    val limitedNumber: String,
    val price: Int?,
    val canBeBought: Boolean?,
    val imgUrl: String?,
    val url: String?,
    @PrimaryKey val id: Int? = null
)

class InvalidFavoriteGpuProductException(message: String): Exception(message)