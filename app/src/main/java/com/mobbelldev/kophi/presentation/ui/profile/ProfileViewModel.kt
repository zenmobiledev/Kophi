package com.mobbelldev.kophi.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.usecase.GetDarkModeUseCase
import com.mobbelldev.kophi.domain.usecase.LogoutUseCase
import com.mobbelldev.kophi.domain.usecase.SetDarkModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    suspend fun getDarkMode(): Flow<Boolean> = getDarkModeUseCase()

    suspend fun setDarkMode(isDarkMode: Boolean) = setDarkModeUseCase(
        isDarkMode = isDarkMode
    )

    fun logout() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logoutUseCase()
            }
        }
    }
}