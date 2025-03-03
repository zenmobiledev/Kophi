package com.mobbelldev.kophi.data.source.remote.model.response


import com.google.gson.annotations.SerializedName

data class CoffeeResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
) {
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("category")
        val category: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("temperature")
        val temperature: List<String?>?,
        @SerializedName("milk_option")
        val milkOption: List<String?>?,
        @SerializedName("sweetness")
        val sweetness: List<String>,
        @SerializedName("price")
        val price: Int,
    )
}