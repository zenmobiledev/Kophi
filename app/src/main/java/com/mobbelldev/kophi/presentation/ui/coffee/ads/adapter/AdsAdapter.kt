package com.mobbelldev.kophi.presentation.ui.coffee.ads.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.mobbelldev.kophi.databinding.ItemAdsImageBinding

class AdsAdapter(
    private val adsList: List<Int>,
    val clickItemListener: (Int) -> Unit,
) :
    RecyclerView.Adapter<AdsAdapter.AdsViewHolder>() {

    inner class AdsViewHolder(private val binding: ItemAdsImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickItemListener(adsList[bindingAdapterPosition])
            }
        }

        fun bind(image: Int) {
            binding.ivAds.load(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder =
        AdsViewHolder(
            ItemAdsImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = adsList.size

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        holder.bind(adsList[position])
    }
}