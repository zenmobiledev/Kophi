package com.mobbelldev.kophi.presentation.ui.coffee.ads

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ActivityAdsBinding
import com.mobbelldev.kophi.presentation.ui.coffee.CoffeeFragment

class AdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageRes = intent.getIntExtra(CoffeeFragment.ADS_IMAGE, 0)
        binding.ivAds.load(imageRes)
    }
}