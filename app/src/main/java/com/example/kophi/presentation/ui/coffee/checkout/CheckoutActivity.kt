package com.example.kophi.presentation.ui.coffee.checkout

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kophi.R
import com.example.kophi.databinding.ActivityCheckoutBinding
import com.example.kophi.presentation.ui.coffee.coffee.adapter.CoffeeAdapter
import com.example.kophi.presentation.ui.coffee.detail.CoffeeDetailActivity
import com.example.kophi.utils.IDRCurrency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding

    private val checkoutViewModel: CheckoutViewModel by viewModels()

    private val coffeeAdapter by lazy {
        CoffeeAdapter {
            val intent = Intent(this@CheckoutActivity, CoffeeDetailActivity::class.java).apply {
                putExtra(CoffeeDetailActivity.COFFEE_DETAIL, it)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupObserver()

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSeeMenu.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                checkoutViewModel.getAllCoffeeProducts()

                launch {
                    checkoutViewModel.coffeeList.collect {
                        if (it.isNotEmpty()) {
                            with(binding) {
                                // Empty cart
                                ivEmptyCart.isVisible = false
                                tvEmptyCart.isVisible = false
                                btnSeeMenu.isVisible = false

                                scrollView.isVisible = true
                                materialCardViewPayment.isVisible = true
                                val totalPrice = it.sumOf { cartCoffee -> cartCoffee.price }
                                tvTotalPrice2.text = IDRCurrency.format(totalPrice)
                            }
                        } else {
                            with(binding) {
                                ivEmptyCart.isVisible = true
                                tvEmptyCart.isVisible = true
                                btnSeeMenu.isVisible = true

                                scrollView.isVisible = false
                                materialCardViewPayment.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvOrder.adapter = coffeeAdapter
    }
}