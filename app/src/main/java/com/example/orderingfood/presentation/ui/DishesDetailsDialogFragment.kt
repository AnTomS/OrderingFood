package com.example.orderingfood.presentation.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.orderingfood.R
import com.example.orderingfood.databinding.FragmentDishDetailBinding
import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.presentation.ui.viewmodels.DishesDetailsViewModel
import javax.inject.Inject

class DishesDetailsDialogFragment(private val dish: Dish) :  DialogFragment() {
    private lateinit var _binding: FragmentDishDetailBinding
    private val binding get() = _binding

    @Inject
    lateinit var dishesDetailsViewModel: DishesDetailsViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_dish_detail, null)

        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val dialog = builder.create()
 //       dialog.window?.setBackgroundDrawableResource(android.R.color.white)
        dialog.window?.decorView?.setBackgroundResource(R.drawable.shape_for_details)
//        dialog.window?.decorView?.background?.setTint(ContextCompat.getColor(requireContext(), R.color.white))
        dialog.show()
        // Add dialog configuration if needed
        val dishImageView: ImageView = dialogView.findViewById(R.id.ivDishImage)
        val dishNameTextView: TextView = dialogView.findViewById(R.id.tvDishName)
        val dishPriceTextView: TextView = dialogView.findViewById(R.id.tvDishPrice)
        val dishWeightTextView: TextView = dialogView.findViewById(R.id.tvDishWeight)
        val dishDescriptionTextView: TextView =
            dialogView.findViewById(R.id.tvDishDescription)
        val dishDialogConfirmButton: Button =
            dialogView.findViewById(R.id.addToCart)
        val dishDialogCloseIcon: ImageButton =
            dialogView.findViewById(R.id.btnClose)

        dishNameTextView.text = dish.name
        dishPriceTextView.text = dish.price.toInt().toString() + " ₽"
        dishWeightTextView.text = " · ${dish.weight.toInt()}г"
        dishDescriptionTextView.text = dish.description

        Glide.with(dishImageView)
            .load(dish.imageUrl)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(RequestOptions().centerInside())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(dishImageView)

        dishDialogConfirmButton.setOnClickListener {

            dismiss()
        }

        dishDialogCloseIcon.setOnClickListener {
            dismiss()
        }

        return dialog
    }
}