package com.example.data.local


import com.example.domain.model.Cart
import com.example.domain.model.CartItem
import com.example.domain.model.Categories
import com.example.domain.model.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LocalDataSource  @Inject constructor(){
    private val cartItems: MutableList<CartItem> = mutableListOf()
    private val _cart: MutableStateFlow<Cart> = MutableStateFlow(Cart())
    val cart: StateFlow<Cart> = _cart

    private val categoriesFlow: MutableStateFlow<List<Categories>> = MutableStateFlow(emptyList())
    private val dishesFlow: MutableStateFlow<List<Dish>> = MutableStateFlow(emptyList())

    // Метод для сохранения списка категорий с использованием Flow
    fun saveCategories(categories: List<Categories>) {
        categoriesFlow.value = categories
    }

    // Метод для сохранения списка блюд с использованием Flow
    fun saveDishes(dishes: List<Dish>) {
        dishesFlow.value = dishes
    }

    // Методы для получения сохраненных данных с использованием Flow
    fun getCategoriesFlow(): Flow<List<Categories>> = categoriesFlow
    fun getDishesFlow(): Flow<List<Dish>> = dishesFlow
    fun getCartItems(): List<CartItem> {
        return cartItems
    }

    fun addToCart(dish: Dish) {
        val existingItem = cartItems.find { it.dish == dish }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(dish, 1))
        }
    }

    fun removeFromCart(dish: Dish) {
        val existingItem = cartItems.find { it.dish == dish }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                cartItems.remove(existingItem)
            }
        }
    }

    fun getCartTotalPrice(): Int {
        var totalPrice = 0
        for (item in cartItems) {
            totalPrice += item.totalPrice
        }
        return totalPrice
    }

    suspend fun increaseDishQuantity(dish: Dish) {
        val cartItem = findCartItem(dish)
        if (cartItem != null) {
            cartItem.quantity++
            saveCart()
        }
    }

    suspend fun decreaseDishQuantity(dish: Dish) {
        val cartItem = findCartItem(dish)
        if (cartItem != null) {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
            } else {
                removeCartItem(cartItem)
            }
            saveCart()
        }
    }

    private fun findCartItem(dish: Dish): CartItem? {
        return _cart.value.items.find { it.dish == dish }
    }

    private fun removeCartItem(cartItem: CartItem) {
        _cart.value.items.remove(cartItem)
    }

    private fun saveCart() {
        // Сохранение состояния корзины
        _cart.value = _cart.value.copy() // Обновляем значение, чтобы сработал Flow
    }
}