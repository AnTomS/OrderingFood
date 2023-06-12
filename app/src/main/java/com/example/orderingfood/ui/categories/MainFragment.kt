package com.example.orderingfood.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.orderingfood.App
import com.example.orderingfood.R
import com.example.orderingfood.databinding.FragmentMainBinding
import com.example.orderingfood.viewmodels.CategoriesViewModel
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var _binding: FragmentMainBinding
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    @Inject
    lateinit var categoriesViewModel: CategoriesViewModel



    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)

        Log.d("MainFragment", "Injection completed MainFragment")

        categoriesViewModel.loadCategories()
        observeCategories()
        setupRecyclerView()
        setupToolbar()
    }


    private fun setupRecyclerView() {
        adapter = CategoriesAdapter()
        binding.recyclerCategories.adapter = adapter
    }

    private fun observeCategories() {
//        categoriesViewModel.categories.observe(viewLifecycleOwner) { categories ->
//            adapter.submitList(categories)
//
//        }
        categoriesViewModel.loadCategories()
        categoriesViewModel.categories.observe(viewLifecycleOwner) { categories ->
            adapter.setCategories(categories)
            Log.d("MainFragment", "Categories: $categories")
        }
    }


    private fun setupToolbar() {
        binding.topToolbar.apply {
            // Настройка тулбара
        }
    }
}