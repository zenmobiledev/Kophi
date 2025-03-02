package com.example.kophi.domain.usecase

import com.example.kophi.domain.repositories.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: CoffeeRepository) {
    fun getDarkMode(): Flow<Boolean> = repository.getDarkMode()

    suspend fun setDarkMode(isDarkMode: Boolean) = repository.setDarkMode(isDarkMode)

    suspend fun logout() = repository.logout()
}