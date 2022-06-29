package com.rulhouse.evgawatcher.methods

import java.text.NumberFormat
import java.util.*

object StringMethods {
    fun getNTFormat(price: Int): String {
        NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
            currency = Currency.getInstance("NTD")
            return format(price)
        }
    }
}