package com.rulhouse.evgawatcher.favorite_products.domain.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        if (list != null) {
            if (list.isNotEmpty()) {
                val gson = Gson()
                return gson.toJson(list)
            }
        }
        return ""
    }
}