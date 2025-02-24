package com.example.kophi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Coffee(
    val `data`: List<Data>,
) {
    @Parcelize
    data class Data(
        val id: Int,
        val image: String,
        val title: String,
        val description: String,
        val price: Int,
//        val milkOption: String,
//        val temperature: List<String>,
    ) : Parcelable
}