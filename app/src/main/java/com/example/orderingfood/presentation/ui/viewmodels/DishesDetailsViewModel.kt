package com.example.orderingfood.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.domain.dto.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesDetailsViewModel @Inject constructor(private val repository: RepositoryImpl) :
    ViewModel() {

    private val _dishesShop = MutableLiveData<List<Pair<Dish, Int>>>()
    val dishesShop: LiveData<List<Pair<Dish, Int>>> get() = _dishesShop

    suspend fun loadDishById(dishId: Int): Dish? {
        return repository.getDishesById(dishId)
    }

    suspend fun getTotalPrice(): Int {
        return repository.getCartTotalPrice()
    }

//    suspend fun getCartItems(): List<Pair<Dish, Int>> {
//        return repository.getCartItems()
//    }

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
        repository.addToCart(dish)
    }

    suspend fun decreaseDishQuantity(dish: Dish) {
        repository.decreaseDishQuantity(dish)
    }

    suspend fun increaseDishQuantity(dish: Dish) {
        repository.increaseDishQuantity(dish)
    }


}
