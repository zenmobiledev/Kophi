package com.example.kophi.domain.usecase

import com.example.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    suspend fun setOnboarding(isOnboarding: Boolean) {
        coffeeRepository.setOnboarding(isOnboarding)
    }

    suspend fun getOnboarding(): Boolean {
        return coffeeRepository.getOnboarding()
    }
}