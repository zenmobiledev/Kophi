package com.mobbelldev.kophi.presentation.ui.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ItemTransactionBinding
import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.presentation.ui.transaction.StatusPayment
import com.mobbelldev.kophi.utils.IDRCurrency
import com.mobbelldev.kophi.utils.capitalizeFirst
import com.mobbelldev.kophi.utils.convertDate

class OuterAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Orders.Data, OuterAdapter.OuterViewHolder>(DIFF_UTIL) {

    inner class OuterViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val innerAdapter = InnerAdapter()

        init {
            binding.rvDetailItem.adapter = innerAdapter
            binding.rvDetailItem.setHasFixedSize(true)
        }

        fun bind(orders: Orders.Data) {
            with(binding) {
                tvDate.text = orders.orCreatedOn.convertDate()
                tvLocationOn.text = "Gandaria City"
                tvStatus.text = orders.orStatus.capitalizeFirst().also {
                    when (orders.orStatus) {
                        StatusPayment.PAID.status -> {
                            tvStatus.background.setTint(
                                itemView.context.resources.getColor(
                                    R.color.green,
                                    itemView.context.resources.newTheme()
                                )
                            ).toString()
                            with(binding) {
                                btnCancel.isVisible = false
                                btnPay.isVisible = false
                            }
                        }

                        StatusPayment.EXPIRED.status -> {
                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.red))
                                .toString()
                            with(binding) {
                                btnCancel.isVisible = false
                                btnPay.isVisible = false
                            }
                        }

                        StatusPayment.CANCELLED.status -> {
                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.gray))
                                .toString()
                            with(binding) {
                                btnCancel.isVisible = false
                                btnPay.isVisible = false
                            }
                        }

                        StatusPayment.PENDING.status -> {
                            tvStatus.background.setTint(itemView.context.resources.getColor(R.color.yellow))
                                .toString()
                            btnPay.apply {
                                val tokenId = orders.orTokenId
                                isVisible = true
                                setOnClickListener {
                                    listener.onClickPay(orderTokenId = tokenId)
                                }
                            }
                            btnCancel.apply {
                                val transactionId = orders.orPlatformId
                                isVisible = true
                                setOnClickListener {
                                    listener.onCancelClick(
                                        transactionId = transactionId
                                    )
                                }
                            }
                        }
                    }
                }
                tvTotalPrice.text = itemView.context.resources.getString(
                    R.string.total_price,
                    IDRCurrency.format(orders.orTotalPrice)
                )

                innerAdapter.submitList(orders.details.first().odProducts)
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