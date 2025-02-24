package com.example.kophi.presentation.ui.coffee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.kophi.databinding.ItemListCoffeeBinding
import com.example.kophi.domain.model.Coffee
import com.example.kophi.utils.IDRCurrency

class CoffeeAdapter(val clickItemListener: (Coffee.Data) -> Unit) :
    ListAdapter<Coffee.Data, CoffeeAdapter.CoffeeViewHolder>(DIFF_UTIL) {
    inner class CoffeeViewHolder(val binding: ItemListCoffeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coffee: Coffee.Data) {
            binding.ivCoffee.load(coffee.image)
            binding.tvTitle.text = coffee.title
            binding.tvDescription.text = coffee.description
            binding.tvPrice.text = IDRCurrency.format(coffee.price)
            binding.root.setOnClickListener {
                clickItemListener(coffee)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder =
        CoffeeViewHolder(
            ItemListCoffeeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Coffee.Data>() {
            override fun areItemsTheSame(
                oldItem: Coffee.Data,
                newItem: Coffee.Data,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Coffee.Data,
                newItem: Coffee.Data,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}