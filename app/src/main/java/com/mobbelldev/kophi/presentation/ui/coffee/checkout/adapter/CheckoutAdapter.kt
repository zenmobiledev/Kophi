package com.mobbelldev.kophi.presentation.ui.coffee.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.mobbelldev.kophi.databinding.ItemCoffeeBinding
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.utils.IDRCurrency

class CheckoutAdapter(private val adapterCallback: AdapterCallback) :
    ListAdapter<CoffeeCart, CheckoutAdapter.CheckoutViewHolder>(
        DIFF_CALLBACK
    ) {

    var isPaymentSelected = false

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

                btnMinus.isEnabled = !isPaymentSelected
                btnPlus.isEnabled = !isPaymentSelected

                btnMinus.setOnClickListener {
                    if (!isPaymentSelected) {
                        if (quantity > 1) {
                            quantity--
                            tvQuantity.text = quantity.toString()
                            adapterCallback.onDecrementQuantity(coffeeCart.id)
                        } else {
                            // Delete item
                            adapterCallback.deleteItem(coffeeCart.id)
                        }
                    }
                }

                btnPlus.setOnClickListener {
                    if (!isPaymentSelected) {
                        quantity++
                        tvQuantity.text = quantity.toString()
                        adapterCallback.onIncrementQuantity(coffeeCart.id)
                    }
                }

                adapterCallback.onUpdateQuantity(
                    cartId = coffeeCart.id,
                    newQuantity = quantity
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CheckoutViewHolder = CheckoutViewHolder(
        ItemCoffeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoffeeCart>() {
            override fun areItemsTheSame(
                oldItem: CoffeeCart,
                newItem: CoffeeCart,
            ): Boolean {
                return oldItem.coffeeId == newItem.coffeeId
            }

            override fun areContentsTheSame(
                oldItem: CoffeeCart,
                newItem: CoffeeCart,
            ): Boolean {
                return oldItem.coffeeId == newItem.coffeeId &&
                        oldItem.quantity == newItem.quantity &&
                        oldItem.subTotal == newItem.subTotal
            }
        }
    }
}