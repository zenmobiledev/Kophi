package com.example.kophi.presentation.ui.coffee.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.kophi.databinding.ItemCoffeeBinding
import com.example.kophi.domain.model.CoffeeCart
import com.example.kophi.utils.IDRCurrency

class CheckoutAdapter : ListAdapter<CoffeeCart, CheckoutAdapter.CheckoutViewHolder>(
    DIFF_CALLBACK
) {
    inner class CheckoutViewHolder(private val binding: ItemCoffeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coffeeCart: CoffeeCart) {
            var quantity = coffeeCart.quantity

            with(binding) {
                ivCoffee.load(coffeeCart.image)
                tvCoffeeName.text = coffeeCart.name
                tvCoffeeDescription.text =
                    "${coffeeCart.temperature}," + " ${coffeeCart.milkOption}," + " ${coffeeCart.sweetness}"
                tvCoffeePrice.text = IDRCurrency.format(coffeeCart.subTotal)
                tvQuantity.text = coffeeCart.quantity.toString()

                btnMinus.setOnClickListener {
                    if (quantity > 1) {
                        quantity--
                        tvQuantity.text = quantity.toString()
                    } else {
                        // Delete item
                        Toast.makeText(
                            itemView.context,
                            "Are you sure delete this item",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }

                btnPlus.setOnClickListener {
                    quantity++
                    tvQuantity.text = quantity.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CheckoutAdapter.CheckoutViewHolder = CheckoutViewHolder(
        ItemCoffeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CheckoutAdapter.CheckoutViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoffeeCart>() {
            override fun areItemsTheSame(
                oldItem: CoffeeCart,
                newItem: CoffeeCart,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CoffeeCart,
                newItem: CoffeeCart,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}