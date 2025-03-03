package com.mobbelldev.kophi.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) :
    ViewModel() {

    fun getDarkMode(): Flow<Boolean> = profileUseCase.getDarkMode()

    suspend fun setDarkMode(isDarkMode: Boolean) {
        profileUseCase.setDarkMode(isDarkMode)
    }

    fun logout() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                profileUseCase.logout()
            }
        }
    }
}