package com.mobbelldev.kophi.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ActivityAppIntroBinding
import com.mobbelldev.kophi.presentation.ui.authentication.AuthenticationActivity
import com.mobbelldev.kophi.presentation.ui.onboarding.adapter.AppIntroViewPager2Adapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AppIntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppIntroBinding

    private val appIntroViewModel: AppIntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAppIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager2.adapter =
            AppIntroViewPager2Adapter(object : AppIntroViewPager2Adapter.IOnButton {
                override fun onClick() {
                    lifecycleScope.launch {
                        // get preference
                        appIntroViewModel.setOnboarding(false)
                        val intent =
                            Intent(
                                this@AppIntroActivity,
                                AuthenticationActivity::class.java
                            ).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        startActivity(intent)
                    }
                }
            })
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { _, _ ->

        }.attach()
    }

    companion object {
        const val MAX_STEP = 3
    }
}