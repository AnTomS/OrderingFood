package com.example.orderingfood.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.domain.dto.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    private val _dishes = MutableLiveData<Result<List<Dish>>>()
    val dishes: MutableLiveData<Result<List<Dish>>> get() = _dishes

    init {
        Log.d("DishesViewModel", "DishesViewModel initialized")
    }

    fun loadDishes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dishesList = repository.getDishes()
                _dishes.postValue(dishesList)
                Log.d("DishesViewModel", "блюда успешно загружены: $dishesList")
            } catch (e: Exception) {
                val errorResult =
                    Result.failure<List<Dish>>(Exception("Ошибка при загрузке категорий: ${e.message}"))
                _dishes.postValue(errorResult)
                Log.d("DishesViewModel", "Ошибка при загрузке категорий: ${e.message}")
            }

        }
    }

}
