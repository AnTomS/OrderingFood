package com.example.orderingfood.data.room

import androidx.room.TypeConverter


class Converters {
    @TypeConverter
    fun fromStringList(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Moshi().fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(value: List<String>?): String {
        return Gson().toJson(value)
    }
}