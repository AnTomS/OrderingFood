package com.example.orderingfood.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.Repository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoriesViewModel::class.java) -> {
                CategoriesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DishesViewModel::class.java) -> {
                DishesViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}