package cn.smaxlyb.livedatademo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareDataViewModel : ViewModel() {
    private val progress: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    fun getProg(): MutableLiveData<Int> = progress

    override fun onCleared() {
        super.onCleared()
    }
}