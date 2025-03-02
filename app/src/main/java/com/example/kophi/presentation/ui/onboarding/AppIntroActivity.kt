package com.example.kophi.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kophi.R
import com.example.kophi.databinding.ActivityAppIntroBinding
import com.example.kophi.presentation.ui.authentication.AuthenticationActivity
import com.example.kophi.presentation.ui.onboarding.adapter.AppIntroViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AppIntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppIntroBinding

    private val appIntroViewModel: AppIntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                lifecycleScope.launch {
                    val isOnboarding = appIntroViewModel.getOnboarding()
                    if (!isOnboarding) {
                        startActivity(
                            Intent(
                                this@AppIntroActivity,
                                AuthenticationActivity::class.java
                            )
                        )
                    }
                }
                false
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAppIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupObserver()
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

    private fun setupObserver() {
        lifecycleScope.launch {
            appIntroViewModel.getDarkMode().collectLatest {
                AppCompatDelegate.setDefaultNightMode(
                    if (it) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    companion object {
        const val MAX_STEP = 3
    }
}