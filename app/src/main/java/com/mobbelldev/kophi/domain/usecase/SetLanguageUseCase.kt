package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class SetLanguageUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(language: String) = repository.setLanguage(
        language = language
    )
}