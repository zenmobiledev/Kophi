package com.example.kophi.data.mapper

import com.example.kophi.data.source.local.entity.CoffeeCartEntity
import com.example.kophi.data.source.remote.model.CoffeeResponse
import com.example.kophi.domain.model.Coffee
import com.example.kophi.domain.model.CoffeeCart
import javax.inject.Inject

class Mapper @Inject constructor() {
    // COFFEE
    fun mapResponseToDomain(response: CoffeeResponse): Coffee {
        return Coffee(
            data = response.data.map { mapResponseToDomain(it) }
        )
    }

    private fun mapResponseToDomain(response: CoffeeResponse.Data): Coffee.Data {
        return Coffee.Data(
            id = response.id,
            category = response.category,
            image = response.image,
            name = response.name,
            description = response.description,
            price = response.price,
            temperature = response.temperature,
            milkOption = response.milkOption,
            sweetness = response.sweetness,
        )
    }


    fun mapDomainToEntities(domain: CoffeeCart): CoffeeCartEntity {
        return CoffeeCartEntity(
            coffeeId = domain.coffeeId,
            id = domain.id,
            image = domain.image,
            name = domain.name,
            temperature = domain.temperature,
            milkOption = domain.milkOption,
            sweetness = domain.sweetness,
            price = domain.price
        )
    }

    fun mapEntityToDomain(entity: List<CoffeeCartEntity>): List<CoffeeCart> {
        return entity.map {
            CoffeeCart(
                coffeeId = it.coffeeId,
                id = it.id,
                image = it.image,
                name = it.name,
                temperature = it.temperature,
                milkOption = it.milkOption,
                sweetness = it.sweetness,
                price = it.price
            )
        }
    }
}