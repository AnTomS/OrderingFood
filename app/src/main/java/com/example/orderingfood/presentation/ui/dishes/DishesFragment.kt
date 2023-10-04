package com.example.orderingfood.presentation.ui.dishes


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.orderingfood.App
import com.example.orderingfood.databinding.ListOfDishesBinding
import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.presentation.ui.DishesDetailsDialogFragment
import com.example.orderingfood.presentation.ui.viewmodels.DishesViewModel
import javax.inject.Inject

class DishesFragment : Fragment(), DishAdapter.OnDishClickListener {

    private lateinit var _binding: ListOfDishesBinding

    private lateinit var dishesAdapter: DishAdapter

    private val binding get() = _binding

    @Inject
    lateinit var dishesViewModel: DishesViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListOfDishesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получите экземпляр компонента из приложения
        val appComponent = (requireActivity().application as App).appComponent

        // Выполните инъекцию зависимостей
        appComponent.inject(this)

        Log.d("DishesFragment", "Injection completed DishesFragment")

        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvDishes.layoutManager = layoutManager

        setupRecyclerView()
        observeDishes()
    }

    private fun setupRecyclerView() {
        dishesAdapter = DishAdapter(this)
        binding.rvDishes.adapter = dishesAdapter
    }

    private fun observeDishes() {
        dishesViewModel.loadDishes()
        dishesViewModel.dishes.observe(viewLifecycleOwner) { dishes ->
            if (dishes != null) {
                dishesAdapter.setDishes(dishes)
            }
            Log.d("DishesFragment", "Dishes: $dishes")
        }
    }


    override fun onDishClick(dish: Dish) {
//        val dishesId = dish.id
//        Log.d("DishesFragment", "Dishes: $dishesId")
//       val action = DishesFragmentDirections.actionDishesFragmentToDishDetailsFragment(dishesId)
//        findNavController().navigate(action)
        val myDialog = DishesDetailsDialogFragment(dish)
        myDialog.show(childFragmentManager, "DishDetailsFragment")
    }
}

/* val myDialog = DishDetailsFragment()
        myDialog.show(childFragmentManager, "DishDetailsFragment")
         это  активация диалогового окна*/