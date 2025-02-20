package com.example.kophi.presentation.ui.coffee.ads.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.kophi.databinding.ItemAdsImageBinding

class AdsAdapter(private val adsList: MutableList<Int>) :
    RecyclerView.Adapter<AdsAdapter.AdsViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener

    inner class AdsViewHolder(private val binding: ItemAdsImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(image: ImageView, url: String)
    }
}