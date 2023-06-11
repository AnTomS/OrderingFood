package com.example.orderingfood.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.orderingfood.App
import com.example.orderingfood.databinding.FragmentMainBinding
import com.example.orderingfood.viewmodels.CategoriesViewModel
import com.example.orderingfood.viewmodels.DishesViewModel


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var adapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)

        setupRecyclerView()
        observeCategories()
        setupToolbar()

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        viewModel.loadCategories()

        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            adapter.submitList(categories)
            Log.d("TAG", "observeCategories: $categories")}
    }


    private fun setupRecyclerView() {
        adapter = CategoriesAdapter()
        binding.recyclerCategories.adapter = adapter
    }

    private fun observeCategories() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            adapter.submitList(categories)
            Log.d("TAG", "observeCategories: $categories")
        }
    }


    private fun setupToolbar() {
        binding.topToolbar.apply {
            // Настройка тулбара
        }
    }
}