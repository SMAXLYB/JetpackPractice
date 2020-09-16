package com.example.databindingdemo.twowaybinding

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class LoginModel(userName: String, rememberMe: Boolean) {
    val userName = ObservableField<String>(userName)
    val rememberMe = ObservableBoolean(rememberMe)
}