package com.example.orderingfood.data.room.db

import androidx.room.TypeConverter
import com.example.orderingfood.data.room.entity.DishesEntity
import com.example.orderingfood.domain.dto.Dish
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


class Converters {
    private val moshi = Moshi.Builder().build()
    private val listStringAdapter: JsonAdapter<List<String>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, String::class.java))

    @TypeConverter
    fun fromStringList(value: String?): List<String> {
        return if (value == null) {
            emptyList()
        } else {
            try {
                listStringAdapter.fromJson(value) ?: emptyList()
            } catch (e: JsonDataException) {
                emptyList()
            }
        }
    }

    @TypeConverter
    fun toStringList(value: List<String>?): String {
        return listStringAdapter.toJson(value ?: emptyList())
    }

    fun DishesEntity.toDish(): Dish {
        return Dish(description, id, imageUrl, name, price, tegs, weight)
    }


}