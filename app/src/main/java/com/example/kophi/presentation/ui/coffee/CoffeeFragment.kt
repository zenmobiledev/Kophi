package com.example.kophi.presentation.ui.coffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kophi.R
import com.example.kophi.databinding.FragmentCoffeeBinding
import com.example.kophi.presentation.ui.coffee.adapter.CoffeeAdapter
import com.example.kophi.presentation.ui.coffee.ads.adapter.AdsAdapter
import com.example.kophi.presentation.ui.coffee.data.Coffee
import com.google.android.material.carousel.CarouselSnapHelper

class CoffeeFragment : Fragment() {

    private var _binding: FragmentCoffeeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val coffeeViewModel =
            ViewModelProvider(this)[CoffeeViewModel::class.java]

        _binding = FragmentCoffeeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ADS
        CarouselSnapHelper().attachToRecyclerView(binding.rvAds)
        val adsList = mutableListOf<Int>()
        adsList.add(R.drawable.one)
        adsList.add(R.drawable.two)
        adsList.add(R.drawable.three)
        adsList.add(R.drawable.four)
        adsList.add(R.drawable.five)

        val adsAdapter = AdsAdapter(adsList)
        binding.rvAds.adapter = adsAdapter

//        listOf("Espresso", "Americano", "Cappuccino", "Latte", "Mochaccino", "Macchiato").forEach {
//            binding.tabs.addTab(binding.tabs.newTab().setText(it))
//        }

        // COFFEE
        val coffeeList = mutableListOf<Coffee>()
        coffeeList.addAll(
            listOf(
                Coffee(
                    image = R.drawable.coffee_bean_icon,
                    title = "Espresso 1 shot",
                    description = "String coffee",
                    price = 15000
                ),
                Coffee(
                    image = R.drawable.coffee_bean_icon,
                    title = "Espresso 2 shots",
                    description = "String coffee",
                    price = 15000
                ),
                Coffee(
                    image = R.drawable.coffee_bean_icon,
                    title = "Espresso 3 shots",
                    description = "String coffee",
                    price = 15000
                ),
                Coffee(
                    image = R.drawable.coffee_bean_icon,
                    title = "Espresso 4 shots",
                    description = "String coffee",
                    price = 15000
                )
            )
        )

        val coffeeAdapter = CoffeeAdapter(
            listCoffee = coffeeList
        )
        binding.rvCoffee.adapter = coffeeAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}