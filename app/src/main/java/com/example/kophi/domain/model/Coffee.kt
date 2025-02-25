package com.example.kophi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Coffee(
    val `data`: List<Data>,
) {
    @Parcelize
    data class Data(
        val id: Int,
        val category: String,
        val image: String,
        val name: String,
        val description: String,
        val temperature: List<String?>?,
        val milkOption: List<String?>?,
        val sweetness: List<String?>?,
        val price: Int,
    ) : Parcelable
}