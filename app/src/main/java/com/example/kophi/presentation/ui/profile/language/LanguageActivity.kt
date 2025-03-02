package com.example.kophi.presentation.ui.profile.language

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kophi.R
import com.example.kophi.databinding.ActivityLanguageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding

    private val languageViewModel: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed().also {
                lifecycleScope.launch {
                    languageViewModel.getLanguage().collectLatest {
                        val localeList = LocaleListCompat.forLanguageTags(it)
                        AppCompatDelegate.setApplicationLocales(localeList)
                    }
                }
            }
        }
        lifecycleScope.launch {
            languageViewModel.getLanguage().collectLatest {
                binding.rbEnglish.isChecked = it == Languages.ENGLISH.language
                binding.rbIndonesia.isChecked = it == Languages.INDONESIAN.language
            }
        }


        binding.rbEnglish.setOnClickListener {
            setLocale(Languages.ENGLISH.language)
        }

        binding.rbIndonesia.setOnClickListener {
            setLocale(Languages.INDONESIAN.language)
        }
    }

    private fun setLocale(language: String) {
        lifecycleScope.launch {
            languageViewModel.setLanguage(language)
            val localeList = LocaleListCompat.forLanguageTags(language)
            AppCompatDelegate.setApplicationLocales(localeList)
        }
    }
}