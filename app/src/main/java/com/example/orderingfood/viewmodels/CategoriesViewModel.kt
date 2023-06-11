package com.example.orderingfood.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.Repository
import com.example.data.repository.RepositoryImpl
import com.example.domain.model.Categories
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: RepositoryImpl) : ViewModel() {
    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    fun loadCategories() {
        viewModelScope.launch {
            try {
                // Выполняем загрузку данных из источника данных
                val categoriesList = repository.getCategories()
                _categories.postValue(categoriesList)
            } catch (e: Exception) {
                // Обрабатываем ошибку загрузки данных
            }
        }
    }
}
