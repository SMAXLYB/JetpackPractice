package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadLogWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        private const val TAG = "UploadLogWorker"
    }
    
    /**
     * 后台任务
     * @return Result 执行成功,返回Result.success(),执行失败,返回Result.failure(),需要重新执行,返回Result.retry()
     */
    override fun doWork(): Result {

        // do something
        Log.d(TAG, "doWork: 正在上传日志")

        // 获取输入数据
        val inputData = inputData.getString("InputData")
        Log.d(TAG, "doWork: 输入数据是=$inputData")

        // 设置输出数据
        val outputData = Data.Builder()
            .putString("OutputData","Bye")
            .build()
        // 输出数据
        return Result.success(outputData)
        // return Result.failure(outputData)
        // return Result.retry()
    }
}