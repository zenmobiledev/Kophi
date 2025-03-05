package com.mobbelldev.kophi.presentation.ui.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.mobbelldev.kophi.databinding.ItemDetailTransactionBinding
import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.utils.IDRCurrency

class InnerAdapter :
    ListAdapter<Orders.Data.Detail, InnerAdapter.InnerViewHolder>(DIFF_UTIL) {
    class InnerViewHolder(private val binding: ItemDetailTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Orders.Data.Detail) {
            with(binding) {
                item.odProducts.forEach {
                    ivCoffee.load(it.imageUrl.pdImageUrl)
                    tvCoffeeName.text = "${it.name} x ${it.quantity}"
                    tvSubTotal.text = IDRCurrency.format(it.price)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder =
        InnerViewHolder(
            ItemDetailTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Orders.Data.Detail>() {
            override fun areItemsTheSame(
                oldItem: Orders.Data.Detail,
                newItem: Orders.Data.Detail,
            ): Boolean =
                oldItem.odProducts == newItem.odProducts

            override fun areContentsTheSame(
                oldItem: Orders.Data.Detail,
                newItem: Orders.Data.Detail,
            ): Boolean =
                oldItem == newItem
        }
    }
}
