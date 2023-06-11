package com.example.orderingfood.ui.dishes

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orderingfood.App
import com.example.orderingfood.databinding.ListOfDishesBinding
import com.example.orderingfood.viewmodels.DishesViewModel

class DishesFragment : Fragment() {
    private lateinit var viewModel: DishesViewModel
    private lateinit var dishesAdapter: DishesAdapter

    private lateinit var binding: ListOfDishesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListOfDishesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получите экземпляр компонента из приложения
        val appComponent = (requireActivity().application as App).appComponent

        // Выполните инъекцию зависимостей
        appComponent.inject(this)

        // Настройка RecyclerView и адаптера с GridLayoutManager
        dishesAdapter = DishesAdapter(emptyList()) { dish ->
            // Обработка нажатия на блюдо
            // Открытие экрана с деталями блюда (Продукт)
        }
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = dishesAdapter

        // Инициализация ViewModel
        viewModel = ViewModelProvider(this).get(DishesViewModel::class.java)

        // Наблюдение за изменением списка блюд
        viewModel.dishes.observe(viewLifecycleOwner) { dishes ->
            dishesAdapter.setItems(dishes)
        }

        // Получение списка блюд
        viewModel.getDishes()
    }
}