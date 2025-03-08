package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class SetOnBoardingUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(isOnboarding: Boolean) = repository.setOnboarding(
        isOnboarding = isOnboarding
    )
}