package com.example.kophi.presentation.ui.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kophi.R
import com.example.kophi.databinding.ItemTransactionBinding
import com.example.kophi.domain.model.Transaction
import com.example.kophi.presentation.ui.transaction.StatusPayment
import com.example.kophi.utils.IDRCurrency
import com.example.kophi.utils.convertDate

class OuterAdapter :
    ListAdapter<Transaction.Data, OuterAdapter.OuterViewHolder>(DIFF_UTIL) {
    class OuterViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val innerAdapter = InnerAdapter()

        init {
            binding.rvDetailItem.adapter = innerAdapter
            binding.rvDetailItem.setHasFixedSize(true)
        }

        fun bind(transaction: Transaction.Data) {
            with(binding) {
                tvDate.text = transaction.time.convertDate()
                tvLocationOn.text = transaction.location
                tvStatus.text = transaction.paymentStatus.also {
                    when (transaction.paymentStatus) {
                        StatusPayment.PAID.status -> {
                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.green))
                                .toString()
                        }

                        StatusPayment.FAILED.status -> {
                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.red))
                                .toString()
                        }

                        StatusPayment.EXPIRED.status -> {
                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.yellow))
                                .toString()
                        }

                        else -> {
                        }
                    }
                }
                tvTotalPrice.text = itemView.context.resources.getString(
                    R.string.total_price,
                    IDRCurrency.format(transaction.totalAmount)
                )

                innerAdapter.submitList(transaction.items)
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
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Transaction.Data>() {
            override fun areItemsTheSame(
                oldItem: Transaction.Data,
                newItem: Transaction.Data,
            ): Boolean =
                oldItem.transactionId == newItem.transactionId

            override fun areContentsTheSame(
                oldItem: Transaction.Data,
                newItem: Transaction.Data,
            ): Boolean =
                oldItem == newItem

        }
    }
}