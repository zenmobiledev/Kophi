package com.mobbelldev.kophi.presentation.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.model.Authentication
import com.mobbelldev.kophi.domain.usecase.AuthenticationUseCase
import com.mobbelldev.kophi.domain.usecase.GetTokenUseCase
import com.mobbelldev.kophi.domain.usecase.SaveTokenUseCase
import com.mobbelldev.kophi.domain.usecase.SaveUserIdUseCase
import com.mobbelldev.kophi.domain.usecase.SetEmailUseCase
import com.mobbelldev.kophi.utils.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val setEmailUseCase: SetEmailUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {
    private val _dataUser = MutableStateFlow<Authentication.Data?>(null)
    val dataUser: StateFlow<Authentication.Data?> = _dataUser

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    fun continueWithGoogle(rememberMe: Boolean, token: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                authenticationUseCase(
                    rememberMe = rememberMe,
                    token = token
                ).collect { result ->
                    when (result) {
                        is ResultResponse.Error -> {
                            _isLoading.value = false
                            _errorMessage.emit(result.message)
                        }

                        is ResultResponse.Loading -> _isLoading.value = true
                        is ResultResponse.Success -> {
                            _isLoading.value = false
                            _dataUser.value = result.data?.data.also { data ->
                                if (data != null) {
                                    saveUserIdUseCase.invoke(
                                        userId = data.usId
                                    )
                                    saveTokenUseCase.invoke(
                                        token = data.token
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    suspend fun getToken(): String = getTokenUseCase.invoke()

    suspend fun setEmail(email: String) = setEmailUseCase.invoke(
        email = email
    )
}