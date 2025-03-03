package com.mobbelldev.kophi.domain.model

data class Transaction(
    val `data`: List<Data>,
) {
    data class Data(
        val items: List<Item>,
        val location: String,
        val paymentStatus: String,
        val time: String,
        val totalAmount: Int,
        val transactionId: String
    ) {
        data class Item(
            val id: Int,
            val image: String,
            val name: String,
            val description: String,
            val price: Int,
            val quantity: Int,
            val subTotal: Int,
        )
    }
}
