package com.rulhouse.evgawatcher.util

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

    fun removeStringCharacter(str: String, charList: List<Char>): String {
        if (str.isEmpty())
            return ""
        var nowStr = str
        charList.forEach { char ->
            if (nowStr.contains(char)) {
                val removedIndex = nowStr.indexOf(char)
                // If the char doesn't exist
                if (removedIndex != -1) {
                    nowStr = nowStr.substring(0, removedIndex) +
                            nowStr.substring(removedIndex + 1, nowStr.length)
                }
            }
        }
        return nowStr
    }
}