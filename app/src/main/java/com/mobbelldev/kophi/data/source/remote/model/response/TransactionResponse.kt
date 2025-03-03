package com.mobbelldev.kophi.data.source.remote.model.response


import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("location")
        val location: String,
        @SerializedName("payment_status")
        val paymentStatus: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("total_amount")
        val totalAmount: Int,
        @SerializedName("transaction_id")
        val transactionId: String
    ) {
        data class Item(
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("price")
            val price: Int,
            @SerializedName("quantity")
            val quantity: Int,
            @SerializedName("sub_total")
            val subTotal: Int,
        )
    }
}