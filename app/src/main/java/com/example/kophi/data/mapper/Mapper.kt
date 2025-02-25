package com.example.kophi.data.mapper

import com.example.kophi.data.source.local.entity.CartCoffeeEntity
import com.example.kophi.data.source.local.entity.CoffeeEntity
import com.example.kophi.data.source.remote.model.CoffeeResponse
import com.example.kophi.domain.model.CartCoffee
import com.example.kophi.domain.model.Coffee
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
            title = response.title,
            description = response.description,
            price = response.price,
            temperature = response.temperature,
            milkOption = response.milkOption,
            sweetness = response.sweetness,
        )
    }

    fun mapDomainToEntities(domain: Coffee.Data): CoffeeEntity {
        return CoffeeEntity(
            id = domain.id,
            category = domain.category,
            image = domain.image,
            title = domain.title,
            description = domain.description,
            temperature = domain.temperature,
            milkOption = domain.milkOption,
            price = domain.price,
            sweetness = domain.sweetness,
        )
    }

    fun mapDomainToEntities(domain: CartCoffee): CartCoffeeEntity {
        return CartCoffeeEntity(
            id = domain.id,
            title = domain.title,
            temperature = domain.temperature,
            milkOption = domain.milkOption,
            sweetness = domain.sweetness,
            price = domain.price,
        )
    }
}