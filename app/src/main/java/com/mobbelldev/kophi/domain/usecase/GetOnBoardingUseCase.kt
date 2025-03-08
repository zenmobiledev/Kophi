package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class GetOnBoardingUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(): Boolean = repository.getOnboarding()
}