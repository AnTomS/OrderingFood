package com.example.orderingfood.data.local


class LocalDataSource () {
//    private val cartItems: MutableList<CartItem> = mutableListOf()
//    private val _cart: MutableStateFlow<Cart> = MutableStateFlow(Cart())
//    val cart: StateFlow<Cart> = _cart
//
//    private val categoriesFlow: MutableStateFlow<List<Categories>> = MutableStateFlow(emptyList())
//    private val dishesFlow: MutableStateFlow<List<Dish>> = MutableStateFlow(emptyList())
//
//    // Метод для сохранения списка категорий с использованием Flow
//    suspend fun saveCategories(categories: List<Categories>) {
//        categoriesFlow.value = categories
//        Log.d("LocalDataSource", "Categories saved: $categories")
//    }
//
//    // Метод для сохранения списка блюд с использованием Flow
//    suspend fun saveDishes(dishes: List<Dish>) {
//        dishesFlow.value = dishes
//        Log.d("LocalDataSource", "com.example.orderingfood.domain.dto.Dishes saved: $dishes")
//    }
//
//    // Методы для получения сохраненных данных с использованием Flow
//    fun getCategoriesFlow(): Flow<List<Categories>> = categoriesFlow
//    fun getDishesFlow(): Flow<List<Dish>> = dishesFlow
//    fun getCartItems(): List<CartItem> {
//        return cartItems
//    }
//
//    suspend fun addToCart(dish: Dish) {
//        val existingItem = cartItems.find { it.dish == dish }
//        if (existingItem != null) {
//            existingItem.quantity++
//        } else {
//            cartItems.add(CartItem(dish, 1))
//        }
//        Log.d("LocalDataSource", "Dish added to cart: $dish")
//    }
//
//    suspend fun removeFromCart(dish: Dish) {
//        val existingItem = cartItems.find { it.dish == dish }
//        if (existingItem != null) {
//            if (existingItem.quantity > 1) {
//                existingItem.quantity--
//            } else {
//                cartItems.remove(existingItem)
//            }
//        }
//        Log.d("LocalDataSource", "Dish removed from cart: $dish")
//    }
//
//    fun getCartTotalPrice(): Int {
//        var totalPrice = 0
//        for (item in cartItems) {
//            totalPrice += item.totalPrice
//        }
//        Log.d("LocalDataSource", "Cart total price: $totalPrice")
//        return totalPrice
//    }
//
//    suspend fun increaseDishQuantity(dish: Dish) {
//        val cartItem = findCartItem(dish)
//        if (cartItem != null) {
//            cartItem.quantity++
//            saveCart()
//            Log.d("LocalDataSource", "Dish quantity increased: $dish")
//        }
//    }
//
//    suspend fun decreaseDishQuantity(dish: Dish) {
//        val cartItem = findCartItem(dish)
//        if (cartItem != null) {
//            if (cartItem.quantity > 1) {
//                cartItem.quantity--
//            } else {
//                removeCartItem(cartItem)
//            }
//            saveCart()
//            Log.d("LocalDataSource", "Dish quantity decreased: $dish")
//        }
//    }
//
//    private fun findCartItem(dish: Dish): CartItem? {
//        return _cart.value.items.find { it.dish == dish }
//    }
//
//    private fun removeCartItem(cartItem: CartItem) {
//        _cart.value.items.remove(cartItem)
//    }
//
//    private fun saveCart() {
//        // Сохранение состояния корзины
//        _cart.value = _cart.value.copy() // Обновляем значение, чтобы сработал Flow
//        Log.d("LocalDataSource", "Cart saved: ${_cart.value}")
//    }
}