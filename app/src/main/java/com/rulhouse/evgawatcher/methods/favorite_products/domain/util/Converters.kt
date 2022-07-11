package com.rulhouse.evgawatcher.methods.favorite_products.domain.util

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        if (value == null || value.isBlank()) return listOf()

        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        if (list == null || list.isEmpty()) return ""

        val gson = Gson()
        return gson.toJson(list)
    }
}