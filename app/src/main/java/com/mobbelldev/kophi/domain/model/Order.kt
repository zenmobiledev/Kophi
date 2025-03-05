package com.mobbelldev.kophi.domain.model

import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest.Callbacks
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest.Item

data class Order(
    val amount: Int,
    val callbacks: Callbacks,
    val email: String,
    val items: List<Item>,
    val promoCodes: List<String>,
) {
    data class Callbacks(
        val error: String,
        val finish: String,
    )

    data class Item(
        val id: Int,
        val name: String,
        val price: Int,
        val quantity: Int,
    )
}