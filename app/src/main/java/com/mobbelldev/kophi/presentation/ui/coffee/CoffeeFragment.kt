package com.mobbelldev.kophi.presentation.ui.coffee

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.tabs.TabLayout
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.FragmentCoffeeBinding
import com.mobbelldev.kophi.domain.model.Coffee
import com.mobbelldev.kophi.presentation.ui.coffee.adapter.CoffeeAdapter
import com.mobbelldev.kophi.presentation.ui.coffee.ads.AdsActivity
import com.mobbelldev.kophi.presentation.ui.coffee.ads.adapter.AdsAdapter
import com.mobbelldev.kophi.presentation.ui.coffee.checkout.CheckoutActivity
import com.mobbelldev.kophi.presentation.ui.coffee.detail.CoffeeDetailActivity
import com.mobbelldev.kophi.utils.IDRCurrency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoffeeFragment : Fragment() {

    private var _binding: FragmentCoffeeBinding? = null
    private val binding get() = _binding!!

    private val coffeeViewModel: CoffeeViewModel by viewModels()

    private val adsList = listOf(
        R.drawable.one, R.drawable.two,
        R.drawable.three, R.drawable.four, R.drawable.five
    )

    private val adsAdapter by lazy {
        AdsAdapter(adsList) {
            val intent = Intent(requireContext(), AdsActivity::class.java)
            intent.putExtra(ADS_IMAGE, it)
            startActivity(intent)
        }
    }

    private val coffeeAdapter by lazy {
        CoffeeAdapter {
            val intent = Intent(requireContext(), CoffeeDetailActivity::class.java).apply {
                putExtra(CoffeeDetailActivity.COFFEE_DETAIL, it)
            }
            startActivity(intent)
        }
    }

    private var coffeeList = emptyList<Coffee.Data>()
    private var categoryList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoffeeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ADS
        CarouselSnapHelper().attachToRecyclerView(binding.rvAds)


        setupRecyclerView()
        setupObserver()

        binding.materialCardViewCart.setOnClickListener {
            val intent = Intent(requireContext(), CheckoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnMoveToCheckoutPage.setOnClickListener {
            val intent = Intent(requireContext(), CheckoutActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.rvAds.adapter = adsAdapter
        binding.rvCoffee.adapter = coffeeAdapter
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    coffeeViewModel.isLoading.collect {
                        binding.shimmerCoffee.isVisible = it
                    }
                }

                launch {
                    coffeeViewModel.errorMessage.collect {
                        Toast.makeText(view?.context, "Error: $it", Toast.LENGTH_LONG).show()
                    }
                }

                launch {
                    coffeeViewModel.coffeeData.collect { coffee ->
                        coffee?.let {
                            coffeeList = it.data
                            categoryList =
                                coffeeList.map { data -> data.category }.distinct().toMutableList()

                            if (categoryList.isNotEmpty()) {
                                setupViewPager()
                            }
                            coffeeAdapter.submitList(coffeeList.filter { data ->
                                data.category == categoryList.firstOrNull()
                            })

                        }
                    }
                }

                launch {
                    coffeeViewModel.coffeeList.collect { cartList ->
                        binding.materialCardViewCart.isVisible = cartList.isNotEmpty()
                        val bottomPadding = if (cartList.isNotEmpty()) 240 else 0
                        binding.rvCoffee.setPadding(0, 0, 0, bottomPadding)

                        binding.tvTotalItems.text = cartList.sumOf { it.quantity }.toString()
                        binding.tvTotalPrice.text =
                            IDRCurrency.format(cartList.sumOf { it.subTotal })

                        coffeeViewModel.getAllCartCoffees()
                    }
                }
            }
        }
    }

    private fun setupViewPager() {
        binding.tabs.removeAllTabs()

        categoryList.forEach {
            binding.tabs.addTab(binding.tabs.newTab().setText(it))
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                coffeeAdapter.submitList(coffeeList.filter {
                    it.category == categoryList[tab?.position ?: 0]
                })
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
        binding.shimmerTabs.isVisible = false
    }

    companion object {
        const val ADS_IMAGE = "ads_image"
    }
}