package com.example.kophi.data.mapper

import com.example.kophi.data.source.remote.model.CoffeeResponse
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
            image = response.image,
            title = response.title,
            description = response.description,
            price = response.price
        )
    }
}