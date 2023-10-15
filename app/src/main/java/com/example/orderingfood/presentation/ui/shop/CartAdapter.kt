package com.example.orderingfood.presentation.ui.shop


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderingfood.databinding.ListShopCardviewBinding
import com.example.orderingfood.domain.dto.CartItem
import com.example.orderingfood.domain.dto.Dish


class CartAdapter(private val clickListener: OnCartClickListener) :
    ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListShopCardviewBinding.inflate(inflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem, clickListener)
    }

    fun setDishes(dishes: List<Dish>) {
        val cartItems = dishes.map {
            CartItem(
                it,
                1
            )
        } // Устанавливаем начальное количество блюд в корзине как 1
        submitList(cartItems)
    }

    interface OnCartClickListener {
        fun onCartClick(item: CartItem)
        fun onIncreaseDishClick(item: CartItem)
        fun onDecreaseDishClick(item: CartItem)
    }

    class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.dish.id == newItem.dish.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }

    }

    inner class CartViewHolder(private val binding: ListShopCardviewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        init {
            binding.imageIncreaseDish.setOnClickListener(this)
            binding.imageDecreaseDish.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val cartItem = getItem(position)
                when (view) {
                    binding.imageIncreaseDish -> {
                        clickListener.onIncreaseDishClick(cartItem)
                    }

                    binding.imageDecreaseDish -> {
                        clickListener.onDecreaseDishClick(cartItem)
                    }
                }
            }
        }


        fun bind(cartItem: CartItem, clickListener: OnCartClickListener?) {
            binding.apply {
                val dish = cartItem.dish
                val quantity = cartItem.quantity
                binding.apply {
                    // Привязка данных к элементам вёрстки
                    textViewTitle.text = dish.name
                    textViewPrice.text = dish.price.toInt().toString() + " ₽"
                    textViewQuantity.text = "$quantity"
                    textViewWeight.text = " · ${dish.weight.toInt()}г"
                    Glide.with(imageViewDish)
                        .load(dish.imageUrl)
                        .into(imageViewDish)

                    imageIncreaseDish.tag = dish
                    imageDecreaseDish.tag = dish
                }


            }
        }
    }
}