package com.mobbelldev.kophi.data.source.remote.model.request


import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("callbacks")
    val callbacks: Callbacks,
    @SerializedName("email")
    val email: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("promo_codes")
    val promoCodes: List<String>
) {
    data class Callbacks(
        @SerializedName("error")
        val error: String,
        @SerializedName("finish")
        val finish: String
    )

    data class Item(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("quantity")
        val quantity: Int
    )
}