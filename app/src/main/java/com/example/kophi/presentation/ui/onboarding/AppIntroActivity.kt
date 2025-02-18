package com.example.kophi.presentation.ui.onboarding

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kophi.R
import com.example.kophi.data.local.preference.PreferenceParameter
import com.example.kophi.databinding.ActivityAppIntroBinding
import com.example.kophi.presentation.ui.authentication.AuthenticationActivity
import com.example.kophi.presentation.ui.onboarding.adapter.AppIntroViewPager2Adapter
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

        val preference: SharedPreferences =
            getSharedPreferences(PreferenceParameter.MY_PREF, MODE_PRIVATE)
        val isOnboard: Boolean = preference.getBoolean(PreferenceParameter.IS_ONBOARDING, true)

        if (!isOnboard) {
            finish()
            startActivity(
                Intent(
                    this@AppIntroActivity,
                    AuthenticationActivity::class.java
                )
            )
        }

        binding.viewPager2.adapter =
            AppIntroViewPager2Adapter(object : AppIntroViewPager2Adapter.IOnButton {
                override fun onClick() {
                    // get preference
                    println(isOnboard)
                    if (isOnboard) {
                        preference.edit().putBoolean(PreferenceParameter.IS_ONBOARDING, false)
                            .apply()
                        finish()
                        startActivity(
                            Intent(
                                this@AppIntroActivity,
                                AuthenticationActivity::class.java
                            )
                        )
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