package com.mobbelldev.kophi.presentation.ui.coffee.checkout

import android.content.Intent
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
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.databinding.ActivityCheckoutBinding
import com.mobbelldev.kophi.presentation.ui.coffee.checkout.adapter.AdapterCallback
import com.mobbelldev.kophi.presentation.ui.coffee.checkout.adapter.CheckoutAdapter
import com.mobbelldev.kophi.presentation.ui.coffee.payment.PaymentActivity
import com.mobbelldev.kophi.utils.IDRCurrency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity(), AdapterCallback {
    private lateinit var binding: ActivityCheckoutBinding

    private val checkoutViewModel: CheckoutViewModel by viewModels()

    private val checkoutAdapter by lazy {
        CheckoutAdapter(this)
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
                    checkoutViewModel.coffeeList.collect { data ->
                        if (data.isNotEmpty()) {
                            with(binding) {
                                checkoutAdapter.submitList(data.toList())
                                // Empty cart
                                ivEmptyCart.isVisible = false
                                tvEmptyCart.isVisible = false
                                btnSeeMenu.isVisible = false

                                scrollView.isVisible = true
                                materialCardViewPayment.isVisible = true
                                val totalPrice = data.sumOf { cartCoffee -> cartCoffee.subTotal }
                                tvTotalPrice1.text = IDRCurrency.format(totalPrice)
                                tvTotalPrice2.text = IDRCurrency.format(totalPrice)

                                btnSelectPayment.setOnClickListener {
                                    lifecycleScope.launch {
                                        val userId = checkoutViewModel.getUserId()
                                        val email = checkoutViewModel.getEmail()
                                        val items = mutableListOf<OrderRequest.Item>()
                                        for (cart in data) {
                                            items.add(
                                                OrderRequest.Item(
                                                    id = cart.coffeeId,
                                                    name = "${cart.name} ${cart.temperature} ${cart.milkOption} ${cart.sweetness}",
                                                    price = cart.price,
                                                    quantity = cart.quantity
                                                )
                                            )
                                        }
                                        checkoutViewModel.createOrderSnap(
                                            email = email,
                                            price = totalPrice,
                                            items = items,
                                            userId = userId
                                        )

                                        checkoutViewModel.urlSnap.collect { url ->
                                            val intent = Intent(
                                                this@CheckoutActivity,
                                                PaymentActivity::class.java
                                            ).apply {
                                                putExtra(EXTRA_URL_SNAP, url)
                                            }
                                            startActivity(intent)
                                        }
                                    }


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

//                launch {
//                    checkoutViewModel.urlSnap.collectLatest {
//                        val intent = Intent(this)
//                    }
//                }

                launch {
                    checkoutViewModel.isLoading.collect {
                        binding.progressBar.isVisible = it
                    }
                }

                launch {
                    checkoutViewModel.errorMessage.collect {
                        Toast.makeText(this@CheckoutActivity, "Error: $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvOrder.adapter = checkoutAdapter
    }

    override fun onUpdateQuantity(cartId: String, newQuantity: Int) {
        checkoutViewModel.updateQuantityAndSubtotal(cartId, newQuantity)
    }

    override fun onIncrementQuantity(cartId: String) {
        checkoutViewModel.incrementQuantity(cartId)
    }

    override fun onDecrementQuantity(cartId: String) {
        checkoutViewModel.decrementQuantity(cartId)
    }

    override fun deleteItem(cartId: String) {
        checkoutViewModel.deleteCoffeeCart(cartId)
    }

    companion object {
        const val EXTRA_URL_SNAP = "extra_url_snap"
    }
}