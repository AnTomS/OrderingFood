package com.example.orderingfood.ui.dishes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.domain.model.dto.Dish
import com.example.orderingfood.databinding.DishesCardviewBinding

class DishesAdapter(
    private var dishes: List<Dish>,
    private val onItemClick: (Dish) -> Unit
) : RecyclerView.Adapter<DishesAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DishesCardviewBinding.inflate(inflater, parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.bind(dish)
    }

    override fun getItemCount(): Int = dishes.size

    fun setItems(dishes: List<Dish>) {
        this.dishes = dishes
        notifyDataSetChanged()
    }

    @Suppress("DEPRECATION")
    inner class DishViewHolder(private val binding: DishesCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val dish = dishes[position]
                    onItemClick.invoke(dish)
                }
            }
        }

        fun bind(dish: Dish) {
            binding.apply {
                // Привязка данных блюда к элементам вёрстки
                nameDish.text = dish.name
                Glide.with(imageViewDish)
                    .load(dish.image_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewDish)
            }
        }
    }
}