package com.example.orderingfood.domain.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/*
"сategories": [
{
    "id": int,
    "name": "String",
    "image_url": "String"
    },
*/

@JsonClass(generateAdapter = true)
data class Categories(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "name")
    val name: String
)