package com.example.kophi.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.convertDate(): String {
    //  2025-01-23
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH)
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: "Invalid Date"
    } catch (e: Exception) {
        "Invalid Date"
    }
}