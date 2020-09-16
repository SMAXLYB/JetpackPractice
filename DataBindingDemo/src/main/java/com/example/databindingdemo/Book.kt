package com.example.databindingdemo

import androidx.databinding.ObservableInt

class Book(val title: String, val author: String){
    var rating: ObservableInt = ObservableInt(0)
}