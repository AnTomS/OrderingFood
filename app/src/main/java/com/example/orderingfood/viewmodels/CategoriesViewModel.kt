package com.example.orderingfood.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RepositoryImpl
import com.example.domain.model.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(private val repository: RepositoryImpl) :
    ViewModel() {
    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    init {
        Log.d("CategoriesViewModel", "CategoriesViewModel initialized")
    }

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val categoriesList = withContext(Dispatchers.IO) {
                    repository.getCategories()
                }
                Log.d("CategoriesViewModel", "Категории успешно загружены: $categoriesList")
                _categories.postValue(categoriesList)
            } catch (e: Exception) {
                Log.e("CategoriesViewModel", "Ошибка при загрузке категорий: ${e.message}")
                // Обрабатываем ошибку загрузки данных
            }
        }
    }
}

//    fun loadCategories() {
//        viewModelScope.launch {
//            try {
//                val categoriesList = repository.getCategories()
//                Log.d("CategoriesViewModel", "Категории успешно загружены: $categoriesList")
//                _categories.postValue(categoriesList)
//            } catch (e: Exception) {
//                Log.e("CategoriesViewModel", "Ошибка при загрузке категорий: ${e.message}")
//                // Обрабатываем ошибку загрузки данных
//            }
//        }
//    }
//}
