package com.example.kophi.presentation.ui.coffee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.kophi.databinding.ItemListCoffeeBinding
import com.example.kophi.presentation.ui.coffee.data.Coffee

class CoffeeAdapter(val listCoffee: List<Coffee>) :
    RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {
    inner class CoffeeViewHolder(val binding: ItemListCoffeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coffee: Coffee) {
            binding.ivCoffee.load(coffee.image)
            binding.tvTitle.text = coffee.title
            binding.tvDescription.text = coffee.description
            binding.tvPrice.text = "Rp ${coffee.price}"
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

    override fun getItemCount(): Int = listCoffee.size

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.bind(listCoffee[position])
    }
}