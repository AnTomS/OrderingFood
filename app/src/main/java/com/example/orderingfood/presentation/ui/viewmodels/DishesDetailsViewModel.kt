package com.example.orderingfood.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.domain.dto.Dish
import javax.inject.Inject

class DishesDetailsViewModel @Inject constructor(private val repository: RepositoryImpl) :
    ViewModel() {

    suspend fun loadDishById(dishId: Int): Dish? {

        return repository.getDishesById(dishId)

    }
}
