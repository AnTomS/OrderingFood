package com.example.orderingfood.presentation.ui.dishesdetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.orderingfood.App
import com.example.orderingfood.databinding.FragmentDishDetailBinding
import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.presentation.ui.viewmodels.DishesDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DishesDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentDishDetailBinding
    private val binding get() = _binding

    @Inject
    lateinit var dishesDetailsViewModel: DishesDetailsViewModel

    // Используем navArgs для получения аргументов
    private val args: DishesDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
 //       (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDishDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appComponent = (requireActivity().application as App).appComponent

//        appComponent.inject(this)

        // Получаем id блюда из аргументов
        val id = args.dishesId
        //       val dish = args.dishesId

        loadDataForDish(id)
        Log.d("DishesFragment", "Injection completed DishDetailsFragment")
    }

    private fun loadDataForDish(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val dish = dishesDetailsViewModel.loadDishById(id)
            // Проверяем, что dish не null и заполняем макет данными
            dish?.let {
                withContext(Dispatchers.Main) {
                    loadDishesDetails(it)
                }
            }
        }
    }


    private fun loadDishesDetails(dish: Dish) {
        binding.apply {
            tvDishDescription.text = dish.description
            tvDishWeight.text = dish.weight.toString()
            tvDishPrice.text = dish.price.toString()
            tvDishName.text = dish.name
            Glide.with(binding.root.context)
                .load(dish.imageUrl)
                .centerCrop()
                .into(ivDishImage)
        }
    }
}
