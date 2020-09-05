package cn.smaxlyb.viewmodeldemo

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timerTask

class TimerViewModel(private val state: SavedStateHandle) : ViewModel() {
    private var mTimer: Timer? = null
    private lateinit var mOnTimeChangeListener: (Int) -> Unit

    init {
        if (!state.contains(CURRENT_SECOND)) {
            state.set(CURRENT_SECOND, 0)
        }
    }

    companion object {
        private const val TAG = "TimerViewModel"
        private const val CURRENT_SECOND = "CurrentSecond"
    }

    private fun getNum() = state.get<Int>(CURRENT_SECOND)


    // 开启一个定时器,定时增加,
    fun startTiming() {
        if (mTimer == null) {

            mTimer = Timer()
            val timerTask = timerTask {
                getNum()?.let {
                    state.set(CURRENT_SECOND,it+1)
                }
                if (mOnTimeChangeListener != null) {
                    getNum()?.let { mOnTimeChangeListener.invoke(it) }
                }
            }

            mTimer!!.schedule(timerTask, 1000, 1000)
        }
    }

    fun setOnTimeChangeListener(onTimeChangeListener: (Int) -> Unit) {
        this.mOnTimeChangeListener = onTimeChangeListener
    }


    // 当ViewModel不需要时,即activity被销毁(除了屏幕旋转)时,此方法才会被调用,做一些释放资源操作
    override fun onCleared() {
        super.onCleared()

        // 资源释放
        Log.d(Companion.TAG, "onCleared: 资源释放")
    }
}