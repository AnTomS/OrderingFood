package com.example.data.repository

import com.example.data.model.Categories
import com.example.data.model.Dish

interface Repository {

    suspend fun getCategories(): List<Categories>

    suspend fun getDishes(): List<Dish>
}