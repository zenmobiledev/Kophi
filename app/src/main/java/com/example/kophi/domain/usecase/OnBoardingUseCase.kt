package com.example.kophi.domain.usecase

import com.example.kophi.domain.repositories.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    fun getDarkMode(): Flow<Boolean> = coffeeRepository.getDarkMode()

    suspend fun setOnboarding(isOnboarding: Boolean) = coffeeRepository.setOnboarding(isOnboarding)

    suspend fun getOnboarding(): Boolean = coffeeRepository.getOnboarding()
}