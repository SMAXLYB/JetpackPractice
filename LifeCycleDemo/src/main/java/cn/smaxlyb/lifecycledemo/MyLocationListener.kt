package cn.smaxlyb.lifecycledemo

import android.app.Activity
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationListener(context: Activity, onLocationChangedListener: OnLocationChangedListener) :
    LifecycleObserver {

    companion object {
        private const val TAG = "MyLocationListener"
    }

    init {
        Log.d(TAG, "MyLocationListener: 位置模块初始化完成")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun startGetLocation() {
        Log.d(TAG, "startGetLocation: 开始获取位置")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun stopGetLocation() {
        Log.d(TAG, "stopGetLocation: 关闭获取位置")
    }
}