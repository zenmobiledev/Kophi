package com.mobbelldev.kophi.presentation.ui.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ItemTransactionBinding
import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.utils.IDRCurrency
import com.mobbelldev.kophi.utils.convertDate

class OuterAdapter :
    ListAdapter<Orders.Data, OuterAdapter.OuterViewHolder>(DIFF_UTIL) {
    class OuterViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val innerAdapter = InnerAdapter()

        init {
            binding.rvDetailItem.adapter = innerAdapter
            binding.rvDetailItem.setHasFixedSize(true)
        }

        fun bind(orders: Orders.Data) {
            with(binding) {
                tvDate.text = orders.orCreatedOn.convertDate()
                tvLocationOn.text = "Gandaria City 8"
//                tvStatus.text = transaction.paymentStatus.also {
//                    when (transaction.paymentStatus) {
//                        StatusPayment.PAID.status -> {
//                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.green))
//                                .toString()
//                        }
//
//                        StatusPayment.FAILED.status -> {
//                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.red))
//                                .toString()
//                        }
//
//                        StatusPayment.EXPIRED.status -> {
//                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.yellow))
//                                .toString()
//                        }
//
//                        else -> {
//                        }
//                    }
//                }
                tvTotalPrice.text = itemView.context.resources.getString(
                    R.string.total_price,
                    IDRCurrency.format(orders.orTotalPrice)
                )

                innerAdapter.submitList(orders.details)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterViewHolder =
        OuterViewHolder(
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: OuterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Orders.Data>() {
            override fun areItemsTheSame(
                oldItem: Orders.Data,
                newItem: Orders.Data,
            ): Boolean =
                oldItem.orId == newItem.orId

            override fun areContentsTheSame(
                oldItem: Orders.Data,
                newItem: Orders.Data,
            ): Boolean =
                oldItem == newItem

        }
    }
}