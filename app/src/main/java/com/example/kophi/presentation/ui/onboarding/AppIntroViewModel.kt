package com.example.kophi.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import com.example.kophi.domain.usecase.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppIntroViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase) :
    ViewModel() {
    suspend fun setOnboarding(isOnboarding: Boolean) {
        onBoardingUseCase.setOnboarding(isOnboarding)
    }

    suspend fun getOnboarding(): Boolean {
        return onBoardingUseCase.getOnboarding()
    }
}