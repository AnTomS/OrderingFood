package com.example.orderingfood.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.data.room.entity.DishesEntity
import com.example.orderingfood.domain.dto.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesDetailsViewModel @Inject constructor(private val repository: RepositoryImpl) :
    ViewModel() {

    private val _dishesShop = MutableLiveData<List<Pair<Dish, Int>>>()
    val dishesShop: LiveData<List<Pair<Dish, Int>>> get() = _dishesShop

    suspend fun loadDishById(dishId: Int): DishesEntity? {
        return repository.getDishesById(dishId)
    }

    suspend fun getTotalPrice(): Int {
        return repository.getCartTotalPrice()
    }

    fun getCartItemsCount() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dishesShopList = repository.getCartItems()
                _dishesShop.postValue(dishesShopList)
                Log.d("DishesViewModel", "блюда успешно загружены: $dishesShopList")
            } catch (e: Exception) {
                Log.d("DishesViewModel", "Ошибка при загрузке категорий: ${e.message}")
            }
        }
    }


    suspend fun removeItem(dish: Dish) {
        repository.removeFromCart(dish)
    }

    fun addItem(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToCart(dish)
            getCartItemsCount()
        }
    }

    fun decreaseDishQuantity(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.decreaseDishQuantity(dish)
            getCartItemsCount()
        }
    }

    fun increaseDishQuantity(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.increaseDishQuantity(dish)
            getCartItemsCount()
        }
    }

    fun removeItemFromCart(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(dish)
            getCartItemsCount()
        }
    }


}
