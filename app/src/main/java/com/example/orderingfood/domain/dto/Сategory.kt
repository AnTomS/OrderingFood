package com.example.orderingfood.domain.dto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Сategory(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "name")
    val name: String
)