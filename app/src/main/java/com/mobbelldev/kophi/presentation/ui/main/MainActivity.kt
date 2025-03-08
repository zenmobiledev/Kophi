package com.mobbelldev.kophi.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobbelldev.kophi.R
import com.mobbelldev.kophi.databinding.ActivityMainBinding
import com.mobbelldev.kophi.presentation.ui.authentication.AuthenticationActivity
import com.mobbelldev.kophi.presentation.ui.authentication.AuthenticationViewModel
import com.mobbelldev.kophi.presentation.ui.onboarding.AppIntroActivity
import com.mobbelldev.kophi.presentation.ui.onboarding.AppIntroViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val appIntroViewModel: AppIntroViewModel by viewModels()
    private val authenticationViewModel: AuthenticationViewModel by viewModels()
    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setupDarkMode()
        setupLanguage()
        splashScreen.setKeepOnScreenCondition { !isReady }

        lifecycleScope.launch {
            val isOnboarding = appIntroViewModel.getOnboarding()
            if (isOnboarding) {
                startActivity(Intent(this@MainActivity, AppIntroActivity::class.java))
                finish()
                return@launch
            }

            val isLoggedIn = authenticationViewModel.getToken()
            if (isLoggedIn.isEmpty()) {
                startActivity(Intent(this@MainActivity, AuthenticationActivity::class.java))
                finish()
                return@launch
            }

            isReady = true
            setContentView(binding.root)
            setupBottomNavigation()
        }

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.container) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    private fun setupDarkMode() {
        lifecycleScope.launch {
            appIntroViewModel.getDarkMode().collect {
                AppCompatDelegate.setDefaultNightMode(
                    if (it) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    private fun setupLanguage() {
        lifecycleScope.launch {
            appIntroViewModel.getLanguage().collect {
                val locale = Locale(it)
                val localeListCompat = LocaleListCompat.create(locale)
                AppCompatDelegate.setApplicationLocales(localeListCompat)
            }
        }
    }
}