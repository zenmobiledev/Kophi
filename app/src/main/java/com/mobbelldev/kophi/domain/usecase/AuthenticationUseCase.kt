package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.Authentication
import com.mobbelldev.kophi.domain.model.ContinueWithGoogle
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(
        rememberMe: Boolean,
        token: String,
    ): Flow<ResultResponse<Authentication>> {
        return repository.continueWithGoogle(
            ContinueWithGoogle(
                rememberMe = rememberMe,
                token = token
            )
        )
    }

    suspend fun saveTokenToDatabase(token: String) {
        repository.saveTokenToDatabase(token)
    }

    suspend fun saveUserId(usId: Int) {
        repository.saveUserId(usId)
    }

    suspend fun getToken(): String {
        return repository.getToken()
    }

    suspend fun setEmail(email: String) {
        repository.setEmail(email)
    }
}