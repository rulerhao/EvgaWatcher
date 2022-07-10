package com.rulhouse.evgawatcher.favorite_products.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class GpuProduct(
    val serial: String,
    val name: String,
    val statement: List<String?>,
    val warranty: String,
    val limitedNumber: String,
    val price: Int?,
    val canBeBought: Boolean?,
    val imgUrl: String?,
    val uri: String?,
    val favorite: Boolean,
    @PrimaryKey val id: Int? = null
) : Parcelable {
    constructor() : this(
        name = "",
        imgUrl = "",
        serial = "",
        canBeBought = false,
        statement = listOf(),
        uri = "",
        limitedNumber = "",
        price = 0,
        warranty = "",
        favorite = false
    )
}

class InvalidFavoriteGpuProductException(message: String) : Exception(message)