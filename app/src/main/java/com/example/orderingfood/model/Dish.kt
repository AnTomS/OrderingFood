package com.example.orderingfood.model

data class Dish(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String,
    val tags: List<String>
)
