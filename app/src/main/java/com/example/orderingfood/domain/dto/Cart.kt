package com.example.orderingfood.domain.dto

data class CartItem(val dish: Dish, var quantity: Int) {
    val totalPrice: Int
        get() = dish.price * quantity
}

data class Cart(
    val items: MutableList<CartItem> = mutableListOf()
) {
    fun addItem(dish: Dish) {
        val existingItem = items.find { it.dish == dish }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            items.add(CartItem(dish, 1))
        }
    }

    fun removeItem(dish: Dish) {
        val existingItem = items.find { it.dish == dish }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                items.remove(existingItem)
            }
        }
    }

    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (item in items) {
            totalPrice += item.totalPrice
        }
        return totalPrice
    }
}