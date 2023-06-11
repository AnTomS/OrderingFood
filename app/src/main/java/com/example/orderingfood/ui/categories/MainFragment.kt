package com.example.orderingfood.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.orderingfood.databinding.FragmentMainBinding
import com.example.orderingfood.viewmodels.CategoriesViewModel


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var adapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeCategories()
        setupToolbar()
        loadCategories()
    }


    private fun setupRecyclerView() {
        adapter = CategoriesAdapter()
        binding.recyclerCategories.adapter = adapter
    }

    private fun observeCategories() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            adapter.submitList(categories)
        }
    }

    private fun loadCategories() {
        viewModel.loadCategories()
        Log.d("TAG", "onViewCreated: ${viewModel.categories.value}")
    }


    private fun setupToolbar() {
        binding.topToolbar.apply {
            // Настройка тулбара
        }
    }
}