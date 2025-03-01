package com.example.kophi.presentation.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kophi.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val authenticationUseCase: AuthenticationUseCase) :
    ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setAuthenticationUser(isAuthentication: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                authenticationUseCase.setAuthenticationUser(
                    isAuthentication
                )
                _isLoading.value = false
            }
        }
    }

    suspend fun getAuthenticationUser(): Boolean {
        return authenticationUseCase.getAuthenticationUser()
    }
}