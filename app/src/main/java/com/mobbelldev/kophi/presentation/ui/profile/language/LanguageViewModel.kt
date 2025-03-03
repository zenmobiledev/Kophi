package com.mobbelldev.kophi.presentation.ui.profile.language

import androidx.lifecycle.ViewModel
import com.mobbelldev.kophi.domain.usecase.LanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageUseCase: LanguageUseCase) :
    ViewModel() {
    suspend fun setLanguage(language: String) = languageUseCase.setLanguage(language)

    fun getLanguage() = languageUseCase.getLanguage()
}