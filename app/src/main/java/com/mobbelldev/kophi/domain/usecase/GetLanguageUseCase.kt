package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(private val repository: CoffeeRepository) {
    operator fun invoke(): Flow<String> = repository.getLanguage()
}