package com.example.data.local

import com.example.domain.model.dto.Categories

interface CategoriesCallBack {
    fun onSuccess(categories: List<Categories>)
    fun onError(errorMessage: String)
}