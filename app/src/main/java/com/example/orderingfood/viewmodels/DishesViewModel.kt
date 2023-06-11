package com.example.orderingfood.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RepositoryImpl
import com.example.domain.model.Dish
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    private val _dishes = MutableLiveData<List<Dish>>()
    val dishes: LiveData<List<Dish>> get() = _dishes

    fun getDishes() {
        viewModelScope.launch {
            try {
                val result = repository.getDishes()
                _dishes.value = result
            } catch (e: Exception) {
                // Обработка ошибок, логирование и т.д.
            }
        }
    }
}