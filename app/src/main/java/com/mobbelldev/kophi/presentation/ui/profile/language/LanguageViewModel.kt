package com.mobbelldev.kophi.presentation.ui.profile.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.usecase.LanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageUseCase: LanguageUseCase) :
    ViewModel() {

    fun setLanguage(language: String) {
        viewModelScope.launch {
            languageUseCase.setLanguage(language)

            val locales = LocaleListCompat.create(Locale(language))
            AppCompatDelegate.setApplicationLocales(locales)
        }
    }

    fun getLanguage() = languageUseCase.getLanguage()
}