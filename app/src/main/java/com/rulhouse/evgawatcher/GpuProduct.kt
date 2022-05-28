package com.rulhouse.evgawatcher

data class GpuProduct(
    val serial: String,
    val name: String,
    val statement: List<String?>,
    val warranty: String,
    val limitedNumber: String,
    val price: Int?,
    val canBeBought: Boolean?,
    val imgUrl: String?
)
