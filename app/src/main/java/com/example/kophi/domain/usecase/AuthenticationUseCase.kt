package com.example.kophi.domain.usecase

import com.example.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend fun setAuthenticationUser(isAuthentication: Boolean) {
        repository.setAuthenticateUser(
            isAuthenticated = isAuthentication
        )
    }

    suspend fun getAuthenticationUser(): Boolean {
        return repository.getAuthenticateUser()
    }
}