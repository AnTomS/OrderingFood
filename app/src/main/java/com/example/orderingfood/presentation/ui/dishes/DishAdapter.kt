package com.example.orderingfood.presentation.ui.dishes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderingfood.databinding.DishesCardviewBinding
import com.example.orderingfood.domain.dto.Dish

class DishAdapter(private val clickListener: OnDishClickListener) :
    ListAdapter<Dish, DishAdapter.DishViewHolder>(DishDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DishesCardviewBinding.inflate(inflater, parent, false)

        return DishViewHolder(binding)

    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dishes = getItem(position)
        holder.bind(dishes, clickListener)
        holder.itemView.tag = dishes
    }

    fun setDishes(dishes: List<Dish>) {
        submitList(dishes)
    }

    interface OnDishClickListener {
        fun onDishClick(dish: Dish)
    }


    inner class DishViewHolder(private val binding: DishesCardviewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {



        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val dishes = getItem(position)
                Log.d("DishAdapter", "Clicked on dish with id: ${dishes.id}")
                clickListener.onDishClick(dishes)
            }
        }


        fun bind(dishes: Dish, clickListener: OnDishClickListener?) {
            binding.apply {
                // Привязка данных к элементам вёрстки
                nameDish.text = dishes.name
                Glide.with(imageViewDish)
                    .load(dishes.imageUrl)
                    .into(imageViewDish)

            }
        }


    }


    class DishDiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }


}