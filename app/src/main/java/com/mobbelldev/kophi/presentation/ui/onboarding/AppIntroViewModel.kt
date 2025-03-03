package com.mobbelldev.kophi.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import com.mobbelldev.kophi.domain.usecase.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AppIntroViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase) :
    ViewModel() {
    fun getDarkMode(): Flow<Boolean> = onBoardingUseCase.getDarkMode()

    suspend fun setOnboarding(isOnboarding: Boolean) {
        onBoardingUseCase.setOnboarding(isOnboarding)
    }

    suspend fun getOnboarding(): Boolean {
        return onBoardingUseCase.getOnboarding()
    }
}