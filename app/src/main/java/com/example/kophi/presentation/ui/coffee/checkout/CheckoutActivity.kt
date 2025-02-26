package com.example.kophi.presentation.ui.coffee.checkout

import android.os.Bundle
import android.widget.Toast
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
import com.example.kophi.presentation.ui.coffee.checkout.adapter.CheckoutAdapter
import com.example.kophi.utils.IDRCurrency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding

    private val checkoutViewModel: CheckoutViewModel by viewModels()

    private val checkoutAdapter by lazy {
        CheckoutAdapter()
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
                checkoutViewModel.getAllCartCoffees()

                launch {
                    checkoutViewModel.coffeeList.collect {
                        if (it.isNotEmpty()) {
                            with(binding) {
                                checkoutAdapter.submitList(it)
                                // Empty cart
                                ivEmptyCart.isVisible = false
                                tvEmptyCart.isVisible = false
                                btnSeeMenu.isVisible = false

                                scrollView.isVisible = true
                                materialCardViewPayment.isVisible = true
                                val totalPrice = it.sumOf { cartCoffee -> cartCoffee.subTotal }
                                tvTotalPrice1.text = IDRCurrency.format(totalPrice)
                                tvTotalPrice2.text = IDRCurrency.format(totalPrice)

                                btnSelectPayment.setOnClickListener {
                                    Toast.makeText(
                                        this@CheckoutActivity,
                                        "Navigato to Midtrans page",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
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
        binding.rvOrder.adapter = checkoutAdapter
    }
}