package com.example.kophi.presentation.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.kophi.R
import com.example.kophi.databinding.ItemOnboardingScreenBinding
import com.example.kophi.presentation.ui.onboarding.AppIntroActivity

class AppIntroViewPager2Adapter(private val listener: IOnButton) :
    RecyclerView.Adapter<AppIntroViewPager2Adapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemOnboardingScreenBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOnboardingScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = AppIntroActivity.MAX_STEP

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.itemView.run {
        mClickListener = listener

        with(holder) {
            if (position == 0) {
                with(binding) {
                    ivImage.setImageResource(R.drawable.coffee_illustration_1)
                    tvTitle.text = context.getString(R.string.intro_title_1)
                    tvSubTitle.text = context.getString(R.string.intro_sub_title_1)
                }
            }
            if (position == 1) {
                with(binding) {
                    ivImage.setImageResource(R.drawable.coffee_illustration_2)
                    tvTitle.text = context.getString(R.string.intro_title_2)
                    tvSubTitle.text = context.getString(R.string.intro_sub_title_2)
                }
            }
            if (position == 2) {
                with(binding) {
                    ivImage.setImageResource(R.drawable.coffee_illustration_3)
                    tvTitle.text = context.getString(R.string.intro_title_3)
                    tvSubTitle.text = context.getString(R.string.intro_sub_title_3)
                    btnToAuthentication.isVisible = true
                    btnToAuthentication.setOnClickListener {
                        if (mClickListener != null) {
                            mClickListener?.onClick()
                        }
                    }
                }
            }

        }
    }

    interface IOnButton {
        fun onClick()
    }

    companion object {
        var mClickListener: IOnButton? = null
    }
}
