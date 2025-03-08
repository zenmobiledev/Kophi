package com.mobbelldev.kophi.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import com.mobbelldev.kophi.domain.usecase.GetDarkModeUseCase
import com.mobbelldev.kophi.domain.usecase.GetLanguageUseCase
import com.mobbelldev.kophi.domain.usecase.GetOnBoardingUseCase
import com.mobbelldev.kophi.domain.usecase.SetOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AppIntroViewModel @Inject constructor(
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setOnBoardingUseCase: SetOnBoardingUseCase,
    private val getOnBoardingUseCase: GetOnBoardingUseCase,
) :
    ViewModel() {
    suspend fun getDarkMode(): Flow<Boolean> = getDarkModeUseCase()

    suspend fun getLanguage(): Flow<String> = getLanguageUseCase()

    suspend fun setOnboarding(isOnboarding: Boolean) = setOnBoardingUseCase(
        isOnboarding = isOnboarding
    )

    suspend fun getOnboarding(): Boolean = getOnBoardingUseCase()
}