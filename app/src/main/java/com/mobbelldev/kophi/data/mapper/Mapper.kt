package com.mobbelldev.kophi.data.mapper

import com.mobbelldev.kophi.data.source.local.entity.CoffeeCartEntity
import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.response.AuthenticationResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CoffeeResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrderSnapResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrdersResponse
import com.mobbelldev.kophi.domain.model.Authentication
import com.mobbelldev.kophi.domain.model.Coffee
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.model.ContinueWithGoogle
import com.mobbelldev.kophi.domain.model.OrderSnap
import com.mobbelldev.kophi.domain.model.Orders
import javax.inject.Inject

class Mapper @Inject constructor() {
    // Authentication
    fun mapDomainToRequest(domain: ContinueWithGoogle): ContinueWithGoogleRequest {
        return ContinueWithGoogleRequest(
            rememberMe = domain.rememberMe,
            token = domain.token
        )
    }

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

    fun mapResponseToDomain(response: AuthenticationResponse): Authentication {
        return Authentication(
            data = mapResponseToDomain(response.data)
        )
    }

    private fun mapResponseToDomain(response: AuthenticationResponse.Data): Authentication.Data {
        return Authentication.Data(
            token = response.token,
            usId = response.usId,
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
            price = domain.price,
            quantity = domain.quantity,
            subTotal = domain.subTotal,
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
                price = it.price,
                quantity = it.quantity,
                subTotal = it.subTotal,
            )
        }
    }

    // SNAP
    fun mapResponseToDomain(response: OrderSnapResponse): OrderSnap {
        return OrderSnap(
            data = mapResponseToDomain(response.data)
        )
    }

    private fun mapResponseToDomain(response: OrderSnapResponse.Data): OrderSnap.Data {
        return OrderSnap.Data(
            orId = response.orId,
            orUsId = response.orUsId,
            orTotalPrice = response.orTotalPrice,
            orCreatedOn = response.orCreatedOn,
            transaction = mapResponseToDomain(response.transaction)
        )
    }

    private fun mapResponseToDomain(response: OrderSnapResponse.Data.Transaction): OrderSnap.Data.Transaction {
        return OrderSnap.Data.Transaction(
            redirectUrl = response.redirectUrl
        )
    }

    // Orders
    fun mapResponseToDomain(response: OrdersResponse): Orders {
        return Orders(
            data = response.data.map { mapResponseToDomain(it) }
        )
    }

    private fun mapResponseToDomain(response: OrdersResponse.Data): Orders.Data {
        return Orders.Data(
            orCreatedOn = response.orCreatedOn,
            orTotalPrice = response.orTotalPrice,
            details = response.details.map { mapResponseToDomain(it) }
        )
    }

    private fun mapResponseToDomain(response: OrdersResponse.Data.Detail): Orders.Data.Detail {
        return Orders.Data.Detail(
            odProducts = response.odProducts.map { mapResponseToDomain(it) }
        )
    }

    private fun mapResponseToDomain(response: OrdersResponse.Data.Detail.OdProduct): Orders.Data.Detail.OdProduct {
        return Orders.Data.Detail.OdProduct(
            id = response.id,
            imageUrl = mapResponseToDomain(response.imageUrl),
            name = response.name,
            price = response.price,
            quantity = response.quantity
        )
    }

    private fun mapResponseToDomain(response: OrdersResponse.Data.Detail.OdProduct.ImageUrl): Orders.Data.Detail.OdProduct.ImageUrl {
        return Orders.Data.Detail.OdProduct.ImageUrl(
            pdImageUrl = response.pdImageUrl
        )
    }
}