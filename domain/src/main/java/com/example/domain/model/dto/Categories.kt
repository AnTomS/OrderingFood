package com.example.domain.model.dto

import com.squareup.moshi.Json


/*
"сategories": [
{
    "id": int,
    "name": "String",
    "image_url": "String"
    },
*/
data class Categories(
    val id: Int,
    val name: String,
    val image_url: String
)