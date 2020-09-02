package cn.smaxlyb.lifecycledemo

import androidx.lifecycle.LifecycleService

//LifeCycleService继承自Service,并且实现了lifeCycleOwner接口
class MyService : LifecycleService() {
    private lateinit var myServiceObserver: MyServiceObserver

    init {
        myServiceObserver = MyServiceObserver()
        lifecycle.addObserver(myServiceObserver)
    }
}