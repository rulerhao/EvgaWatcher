package com.rulhouse.evgawatcher

data class GpuProduct(
    val serial: String,
    val name: String,
    val statement: List<String?>,
    val warranty: Int?,
    val limitedNumber: Int?,
    val price: Int?,
    val canBeBuy: Boolean?,
    val imgUrl: String?
)
