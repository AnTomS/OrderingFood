package com.example.orderingfood.presentation.ui.shop


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderingfood.databinding.ListShopCardviewBinding
import com.example.orderingfood.domain.dto.Dish


class CartAdapter(private val clickListener: OnCartClickListener) :
    ListAdapter<Dish, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListShopCardviewBinding.inflate(inflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(dish, clickListener)
    }

    fun setDishes(dishes: List<Dish>) {
        submitList(dishes)
    }

    interface OnCartClickListener {
        fun onCartClick(dish: Dish)
    }

    class CartDiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }

    }

    inner class CartViewHolder(private val binding: ListShopCardviewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val dish = getItem(position)
                Log.d("CartAdapter", "Clicked on dish with id: ${dish.id}")
                clickListener.onCartClick(dish)
            }
        }


        fun bind(dish: Dish, clickListener: OnCartClickListener?) {
            binding.apply {
                // Привязка данных к элементам вёрстки
                textViewTitle.text = dish.name
                textViewPrice.text = dish.price.toString()
                textViewQuantity.text = dish.id.toString()
                textViewWeight.text = dish.weight.toString()
                Glide.with(imageViewDish)
                    .load(dish.imageUrl)
                    .into(imageViewDish)

            }


        }
    }
}