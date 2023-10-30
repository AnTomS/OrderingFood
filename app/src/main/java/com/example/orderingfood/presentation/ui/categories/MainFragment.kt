package com.example.orderingfood.presentation.ui.categories

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.orderingfood.App
import com.example.orderingfood.R
import com.example.orderingfood.databinding.FragmentMainBinding
import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.presentation.ui.viewmodels.CategoriesViewModel
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main), CategoriesAdapter.OnClickCategory {

    private lateinit var _binding: FragmentMainBinding
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    @Inject
    lateinit var categoriesViewModel: CategoriesViewModel


    private lateinit var adapter: CategoriesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

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

        observeCategories()
        setupRecyclerView()
        setupToolbar()
    }


    private fun setupRecyclerView() {
        adapter = CategoriesAdapter(this)
        binding.recyclerCategories.adapter = adapter
    }

    private fun observeCategories() {
//        categoriesViewModel.categories.observe(viewLifecycleOwner) { categories ->
//            adapter.submitList(categories)
//
//        }
        categoriesViewModel.loadCategories()
        categoriesViewModel.categories.observe(viewLifecycleOwner) { categories ->
            if (categories != null) {
                adapter.setCategories(categories)
            }
            Log.d("MainFragment", "Categories: $categories")
        }
    }


    private fun setupToolbar() {
        binding.topToolbar.apply {
            // Настройка тулбара
        }
    }

    override fun onClickCategories(categories: Categories) {
        when (categories.id) {
            1 -> {
                navigateToDishes()
            }

            2, 3, 4 -> {
                messageForEmptyCategory()
            }

        }
    }

    private fun navigateToDishes() {
        val action = MainFragmentDirections.actionMainFragmentToDishesFragment()
//        val action = MainFragmentDirections.actionMainFragmentToDishesFragment()
        findNavController().navigate(action)
    }

    private fun messageForEmptyCategory() {
        Toast.makeText(
            requireContext(),
            "Категория в процессе наполнения вкусными блюдами",
            Toast.LENGTH_LONG
        ).show()
    }
}