package com.mobbelldev.kophi.presentation.ui.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.mobbelldev.kophi.databinding.ItemDetailTransactionBinding
import com.mobbelldev.kophi.domain.model.Transaction
import com.mobbelldev.kophi.utils.IDRCurrency

class InnerAdapter :
    ListAdapter<Transaction.Data.Item, InnerAdapter.InnerViewHolder>(DIFF_UTIL) {
    class InnerViewHolder(private val binding: ItemDetailTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction.Data.Item) {
            with(binding) {
                ivCoffee.load(item.image)
                tvCoffeeName.text = "${item.name}, ${item.description} x${item.quantity}"
                tvSubTotal.text = IDRCurrency.format(item.subTotal)
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
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Transaction.Data.Item>() {
            override fun areItemsTheSame(
                oldItem: Transaction.Data.Item,
                newItem: Transaction.Data.Item,
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Transaction.Data.Item,
                newItem: Transaction.Data.Item,
            ): Boolean =
                oldItem == newItem
        }
    }
}
