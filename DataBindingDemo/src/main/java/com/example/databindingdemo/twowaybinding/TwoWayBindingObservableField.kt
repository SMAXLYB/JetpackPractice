package com.example.databindingdemo.twowaybinding;

import android.util.Log

// 使用ObservableField包装数据,自动通知数据改变
public class TwoWayBindingObservableField {

    companion object {
        private val TAG: String = this::class.java.name;
    }

    private var loginModel: LoginModel = LoginModel("Tom", true)

    fun getUserName() = loginModel.userName.get()

    fun setUserName(userName: String) {
        loginModel.userName.set(userName)
        Log.d(TAG, "setUserName: " + loginModel.userName.get());
    }

    fun getRememberMe() = loginModel.rememberMe.get()

    fun setRememberMe(rememberMe: Boolean) {
        loginModel.rememberMe.set(rememberMe)
        Log.d(TAG, "setRememberMe: " + loginModel.rememberMe.get());
    }
}
