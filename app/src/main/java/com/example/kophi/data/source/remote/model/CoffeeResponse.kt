package com.example.kophi.data.source.remote.model


import com.google.gson.annotations.SerializedName

data class CoffeeResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("category")
        val category: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("milk_option")
        val milkOption: List<String?>?,
        @SerializedName("price")
        val price: Int,
        @SerializedName("temperature")
        val temperature: List<String>,
        @SerializedName("title")
        val title: String
    )
}