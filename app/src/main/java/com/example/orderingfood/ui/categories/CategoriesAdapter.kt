package com.example.orderingfood.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Categories
import com.example.orderingfood.databinding.CateroryCardviewBinding

class CategoriesAdapter :
    ListAdapter<Categories, CategoriesAdapter.CategoriesViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CateroryCardviewBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categories = getItem(position)
        holder.bind(categories)
    }

    fun setCategories(categories: List<Categories>) {
        submitList(categories)
    }

    inner class CategoriesViewHolder(private val binding: CateroryCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categories: Categories) {
            binding.apply {
                // Привязка данных к элементам вёрстки
                nameForCategory.text = categories.name
                Glide.with(imageForCategory)
                    .load(categories.image_url)
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
}