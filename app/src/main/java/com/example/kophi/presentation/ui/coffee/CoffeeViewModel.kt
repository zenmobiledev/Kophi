package com.example.kophi.presentation.ui.coffee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoffeeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Coffee Fragment"
    }
    val text: LiveData<String> = _text
}