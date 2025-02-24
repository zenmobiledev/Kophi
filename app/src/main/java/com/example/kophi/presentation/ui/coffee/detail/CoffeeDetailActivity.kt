package com.example.kophi.presentation.ui.coffee.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import com.example.kophi.R
import com.example.kophi.databinding.ActivityCoffeeDetailBinding
import com.example.kophi.domain.model.Coffee
import com.example.kophi.presentation.ui.main.MainActivity
import com.example.kophi.utils.IDRCurrency

class CoffeeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoffeeDetailBinding

    private var number: Int = 1
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

                binding.btnAddToCart.apply {
                    text = getString(R.string.add_to_cart, IDRCurrency.format(coffeeDetail.price))
                    setOnClickListener {
                        startActivity(Intent(this@CoffeeDetailActivity, MainActivity::class.java))
                    }
                }
            }
        }

        binding.btnMinus.setOnClickListener {
            if (number == 1) {
                Toast.makeText(
                    this@CoffeeDetailActivity,
                    "cannot be minus",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val qty = --number
                binding.tvItemCount.text = qty.toString()
            }
        }

        binding.btnPlus.setOnClickListener {
            val qty = ++number
            binding.tvItemCount.text = qty.toString()
        }
    }

    companion object {
        const val COFFEE_DETAIL = "coffee_detail"
    }
}