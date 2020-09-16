package com.example.databindingdemo.twowaybinding


// 手动通知数据改变
// class TwoWayBindingObservable : BaseObservable() {
//
//     private var loginModel: LoginModel = LoginModel("Tom",true)
//
//     // 双向绑定
//     @Bindable
//     fun getUserName(): String = loginModel.userName
//
//     // 在用户修改数据时自动调用
//     fun setUserName(userName: String) {
//         // 要判断,否则会循环调用
//         if(userName != loginModel.userName){
//             loginModel.userName = userName
//             // 数据发生修改后通知观察者,观察者则会调用setter方法
//             notifyPropertyChanged(BR.userName)
//         }
//     }
// }