package com.example.kophi.ui.onboarding.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kophi.MainActivity
import com.example.kophi.R
import com.example.kophi.databinding.ActivityAppIntroBinding
import com.example.kophi.ui.onboarding.adapter.AppIntroViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator

class AppIntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAppIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.viewPager2.adapter =
            AppIntroViewPager2Adapter(object : AppIntroViewPager2Adapter.IOnButton {
                override fun onClick() {
                    finish()
                    startActivity(Intent(this@AppIntroActivity, MainActivity::class.java))
                }

            })
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { _, _ ->

        }.attach()
    }

    companion object {
        const val MAX_STEP = 3
    }
}