package com.mobbelldev.kophi.domain.model

data class OrderSnap(
    val `data`: Data,
) {
    data class Data(
        val orId: Int,
        val orUsId: String,
        val orTotalPrice: Int,
        val orCreatedOn: String,
        val transaction: Transaction,
    ) {
        data class Transaction(
            val redirectUrl: String,
        )
    }
}
