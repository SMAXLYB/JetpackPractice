package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressLogWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        private const val TAG = "CompressLogWorker"
    }

    override fun doWork(): Result {
        // do something
        Log.d(Companion.TAG, "doWork: 正在压缩日志")
        return Result.success()
    }
}