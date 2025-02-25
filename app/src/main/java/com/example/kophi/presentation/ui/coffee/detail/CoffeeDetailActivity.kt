package com.example.kophi.presentation.ui.coffee.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import coil3.load
import com.example.kophi.R
import com.example.kophi.databinding.ActivityCoffeeDetailBinding
import com.example.kophi.domain.model.CartCoffee
import com.example.kophi.domain.model.Coffee
import com.example.kophi.presentation.ui.coffee.coffee.CoffeeViewModel
import com.example.kophi.utils.IDRCurrency
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoffeeDetailBinding

    private val coffeeViewModel: CoffeeViewModel by viewModels()

    private var number: Int = 1
    private var originalPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoffeeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val coffeeDetail = intent.getParcelableExtra<Coffee.Data>(COFFEE_DETAIL)
        if (coffeeDetail != null) {
            with(binding) {
                ivCoffeeImage.load(coffeeDetail.image)
                tvCoffeeTitle.text = coffeeDetail.title
                tvCoffeeDescription.text = coffeeDetail.description

                // SELECTED
                coffeeDetail.temperature?.forEach {
                    val chipTemperature = Chip(this@CoffeeDetailActivity).apply {
                        text = it
                        isCheckable = true
                    }
                    cgTemperature.addView(chipTemperature)
                }

                coffeeDetail.milkOption?.forEach {
                    val chipMilkOption = Chip(this@CoffeeDetailActivity)
                    if (it != null) {
                        textMilk.isVisible = true
                        chipMilkOption.apply {
                            text = it
                            isCheckable = true
                        }
                        cgMilk.addView(chipMilkOption)
                    }
                }

                coffeeDetail.sweetness?.forEach {
                    val chipSweetness = Chip(this@CoffeeDetailActivity).apply {
                        text = it
                        isCheckable = true
                    }
                    cgSweetness.addView(chipSweetness)
                }

                (binding.cgTemperature.getChildAt(0) as? Chip)?.isChecked = true
                (binding.cgMilk.getChildAt(0) as? Chip)?.isChecked = true
                (binding.cgSweetness.getChildAt(0) as? Chip)?.isChecked = true
                binding.tvItemCount.text = number.toString()
                originalPrice = coffeeDetail.price
                binding.btnAddToCart.text =
                    getString(R.string.add_to_cart, IDRCurrency.format(originalPrice))

                binding.btnAddToCart.apply {
                    text = getString(R.string.add_to_cart, IDRCurrency.format(coffeeDetail.price))
                    setOnClickListener {
                        val selectedTemperature = findSelectedChipText(binding.cgTemperature)
                        val selectedMilk = findSelectedChipText(binding.cgMilk)
                        val selectedSweetness = findSelectedChipText(binding.cgSweetness)

                        val cartCoffee = CartCoffee(
                            id = coffeeDetail.id,
                            title = coffeeDetail.title,
                            temperature = selectedTemperature,
                            milkOption = selectedMilk,
                            sweetness = selectedSweetness,
                            price = originalPrice * number
                        )

                        val message =
                            "Temperature: $selectedTemperature\nMilk: $selectedMilk\nSweetness: $selectedSweetness"
                        Toast.makeText(this@CoffeeDetailActivity, message, Toast.LENGTH_SHORT)
                            .show()

                        // Insert to Database
//                        coffeeViewModel.insertCoffee(coffee = cartCoffee).also {
//                            startActivity(
//                                Intent(
//                                    this@CoffeeDetailActivity,
//                                    MainActivity::class.java
//                                )
//                            )
//                        }
                    }
                }
            }
        }

        binding.btnMinus.setOnClickListener {
            if (number > 1) {
                number--
                binding.tvItemCount.text = number.toString()
                updateTotalPrice()
            } else {
                Toast.makeText(this, "Cannot be minus", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPlus.setOnClickListener {
            number++
            binding.tvItemCount.text = number.toString()
            updateTotalPrice()
        }
    }

    private fun findSelectedChipText(chipGroup: ChipGroup): String {
        val selectedChipId = chipGroup.checkedChipId
        return if (selectedChipId != -1) {
            val chip = chipGroup.findViewById<Chip>(selectedChipId)
            chip?.text.toString()
        } else {
            "None"
        }
    }

    private fun updateTotalPrice() {
        val totalPrice = originalPrice * number
        binding.btnAddToCart.text = getString(R.string.add_to_cart, IDRCurrency.format(totalPrice))
    }

    companion object {
        const val COFFEE_DETAIL = "coffee_detail"
    }
}