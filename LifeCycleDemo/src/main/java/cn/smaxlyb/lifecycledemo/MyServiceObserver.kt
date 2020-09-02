package cn.smaxlyb.lifecycledemo

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyServiceObserver : LifecycleObserver {

    companion object {
        private const val TAG = "MyServiceObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun startGetLocation() {
        Log.d(TAG, "startGetLocation: 开始定位")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun stopGetLocation(){
        Log.d(TAG, "stopGetLocation: 关闭定位")
    }
}