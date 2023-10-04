package com.example.orderingfood.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.domain.dto.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(private val repository: RepositoryImpl) :
    ViewModel() {
    private val _categories = MutableLiveData<List<Categories>?>()
    val categories: MutableLiveData<List<Categories>?> get() = _categories

    init {
        Log.d("CategoriesViewModel", "CategoriesViewModel initialized")
    }

    fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val categoriesList = repository.getCategories()
                _categories.postValue(categoriesList)
                Log.d("CategoriesViewModel", "Категории успешно загружены: $categoriesList")
            } catch (e: Exception) {
                Log.d("CategoriesViewModel", "Ошибка при загрузке категорий: ${e.message}")
                // Обработка ошибки, если не удалось загрузить категории
            }
        }
    }
}


//        viewModelScope.launch {
//            try {
//                val categoriesList = withContext(Dispatchers.IO) {
//                    repository.getCategories()
//                }
//                Log.d("CategoriesViewModel", "Категории успешно загружены: $categoriesList")
//                _categories.postValue()
//            } catch (e: Exception) {
//                Log.e("CategoriesViewModel", "Ошибка при загрузке категорий: ${e.message}")
//                // Обрабатываем ошибку загрузки данных
//            }
//        }
//    }


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
