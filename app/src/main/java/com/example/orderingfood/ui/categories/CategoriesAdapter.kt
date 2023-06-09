package com.example.orderingfood.ui.categories

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class CategoriesAdapter
@Inject constructor() :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Определите элементы UI, например, TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Создайте ViewHolder и свяжите его с макетом элемента списка
        TODO()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Свяжите данные категории с элементом списка
    }

    override fun getItemCount(): Int {
        return 1
        // Верните количество элементов списка
    }
}