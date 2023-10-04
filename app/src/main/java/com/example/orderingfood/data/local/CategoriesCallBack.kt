package com.example.orderingfood.data.local

import com.example.orderingfood.domain.dto.Categories

interface CategoriesCallBack {
    fun onSuccess(categories: List<Categories>)
    fun onError(errorMessage: String)
}