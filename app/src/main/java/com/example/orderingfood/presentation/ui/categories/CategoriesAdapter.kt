package com.example.orderingfood.presentation.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderingfood.databinding.CateroryCardviewBinding
import com.example.orderingfood.domain.dto.Categories

class CategoriesAdapter(val listenerCategory: OnClickCategory) :
    ListAdapter<Categories, CategoriesAdapter.CategoriesViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CateroryCardviewBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categories = getItem(position)
        holder.bind(categories, listenerCategory)
        holder.itemView.tag = categories
    }

    fun setCategories(categoriesResult: Result<List<Categories>>) {
        val categories = categoriesResult.getOrDefault(emptyList())
        submitList(categories)
    }

    inner class CategoriesViewHolder(private val binding: CateroryCardviewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val categories = getItem(position)
                listenerCategory.onClickCategories(categories)
            }

        }


        fun bind(categories: Categories, listenerCategory: OnClickCategory) {
            binding.apply {
                // Привязка данных к элементам вёрстки
                nameForCategory.text = categories.name
                Glide.with(imageForCategory)
                    .load(categories.imageUrl)
                    .into(imageForCategory)

            }

        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }
    }

    interface OnClickCategory {
        fun onClickCategories(categories: Categories) {}
    }
}