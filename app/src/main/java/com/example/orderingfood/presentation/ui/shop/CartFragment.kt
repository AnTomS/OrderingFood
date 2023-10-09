package com.example.orderingfood.presentation.ui.shop

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.orderingfood.App
import com.example.orderingfood.databinding.FragmentShopBinding
import com.example.orderingfood.domain.dto.CartItem
import com.example.orderingfood.presentation.ui.viewmodels.DishesDetailsViewModel
import javax.inject.Inject


class CartFragment : Fragment(), CartAdapter.OnCartClickListener {
    private lateinit var _binding: FragmentShopBinding
    private lateinit var cartAdapter: CartAdapter
    private val binding get() = _binding

    @Inject
    lateinit var cartViewModel: DishesDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appComponent = (requireActivity().application as App).appComponent
        appComponent.inject(this)
        setupRecyclerView()
        observeDishes()
        Log.d("CartFragment", "Injection completed CartFragment")
    }

    override fun onCartClick(item: CartItem) {
        Log.d("CartFragment", "Clicked on dish with id: ${item.dish.id}")
    }

    override fun onIncreaseDishClick(item: CartItem) {
        cartViewModel.increaseDishQuantity(item.dish)
        Log.d("CartFragment", "увеличен количество блюда: ${item.quantity}")
    }

    override fun onDecreaseDishClick(item: CartItem) {
        cartViewModel.decreaseDishQuantity(item.dish)
        Log.d("CartFragment", "уменьшено количество блюда: ${item.quantity}")
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(this)
        binding.listShop.adapter = cartAdapter
    }

    private fun observeDishes() {
        cartViewModel.getCartItemsCount()
        cartViewModel.dishesShop.observe(viewLifecycleOwner) { dishesShop ->
            if (dishesShop != null) {
                val cartItems = dishesShop.map { (dish, quantity) -> CartItem(dish, quantity) }
                cartAdapter.submitList(cartItems)
            }
            Log.d("CartFragment", "Dishes: $dishesShop")
        }
    }
}