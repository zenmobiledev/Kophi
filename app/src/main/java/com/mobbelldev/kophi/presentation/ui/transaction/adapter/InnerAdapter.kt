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
    ListAdapter<Orders.Data.Detail.OdProduct, InnerAdapter.InnerViewHolder>(DIFF_UTIL) {
    class InnerViewHolder(private val binding: ItemDetailTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Orders.Data.Detail.OdProduct) {
            with(binding) {
                ivCoffee.load(item.imageUrl.pdImageUrl)
                tvCoffeeName.text = "${item.name} x ${item.quantity}"
                tvSubTotal.text = IDRCurrency.format(item.price)
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
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Orders.Data.Detail.OdProduct>() {
            override fun areItemsTheSame(
                oldItem: Orders.Data.Detail.OdProduct,
                newItem: Orders.Data.Detail.OdProduct,
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Orders.Data.Detail.OdProduct,
                newItem: Orders.Data.Detail.OdProduct,
            ): Boolean =
                oldItem == newItem
        }
    }
}
