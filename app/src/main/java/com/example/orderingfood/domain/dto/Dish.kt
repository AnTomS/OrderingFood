package com.example.orderingfood.domain.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dish(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "tegs")
    val tegs: List<String>,
    @Json(name = "weight")
    val weight: Int
)
