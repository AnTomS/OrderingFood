package com.example.orderingfood.domain.dto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dishes(
    @Json(name = "dishes")
    val dishes: List<Dish>
)