package com.example.kophi.presentation.ui.coffee.coffee

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kophi.R
import com.example.kophi.databinding.FragmentCoffeeBinding
import com.example.kophi.presentation.ui.coffee.adapter.CoffeeAdapter
import com.example.kophi.presentation.ui.coffee.ads.AdsActivity
import com.example.kophi.presentation.ui.coffee.ads.adapter.AdsAdapter
import com.example.kophi.presentation.ui.coffee.detail.CoffeeDetailActivity
import com.google.android.material.carousel.CarouselSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoffeeFragment : Fragment() {

    private var _binding: FragmentCoffeeBinding? = null
    private val binding get() = _binding!!

    private lateinit var coffeeViewModel: CoffeeViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        coffeeViewModel = ViewModelProvider(this)[CoffeeViewModel::class.java]

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
                        binding.shimmer.isVisible = it
                    }
                }

                launch {
                    coffeeViewModel.errorMessage.filterNotNull().collect {
                        Toast.makeText(view?.context, "Error: $it", Toast.LENGTH_LONG).show()
                    }
                }

                launch {
                    coffeeViewModel.coffeeData.collect {
                        coffeeAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    companion object {
        const val ADS_IMAGE = "ads_image"
    }
}