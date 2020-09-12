package com.example.workmanagerdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startOneTimeTask()
    }

    /**
     * 开启一次性任务
     */
    private fun startOneTimeTask() {
        /**
         * 设置任务触发条件,比如需要网络时,电量充足时等,如果在运行时某个条件不能满足,WorkManager将停止工作器,当条件满足时会重新尝试执行改任务
         */
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        // 设置输入数据
        val inputData = Data.Builder()
            .putString("InputData", "Hello world")
            .build()

        /**
         * 设定任务执行周期,一次性任务
         */
        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
            // 设置触发条件
            // .setConstraints(constraints)
            // 如果没有触发条件,或者触发条件已经满足,可能导致任务立即执行,可以调用setInitialDelay方法设置延迟执行
            .setInitialDelay(5, TimeUnit.MINUTES)
            // 如果任务需要重试,系统有默认的重试策略,调用setBackoffCriteria方法自定义策略
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            // 对任务进行标记,方便后续监视任务状态
            .addTag("UploadTag")
            .setInputData(inputData)
            .build()

        // 将任务提交至系统
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)

        // 实时监听任务状态
        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this@MainActivity) {
                if (it != null && it.state == WorkInfo.State.SUCCEEDED) {
                    Toast.makeText(this, "任务已完成", Toast.LENGTH_SHORT).show()
                    // 完成任务后输出数据
                    Log.d(TAG, "outputData:${it.outputData}")
                }
            }
    }

    /**
     * 开启周期性任务,间隔至少15分钟
     */
    private fun startPeriodicTask() {
        val uploadLogWorker =
            PeriodicWorkRequest.Builder(UploadLogWorker::class.java, 15, TimeUnit.MINUTES)
                .addTag(TAG)
                .build()

        WorkManager.getInstance(this).enqueue(uploadLogWorker)

        WorkManager.getInstance(this)
            .getWorkInfosByTagLiveData(TAG)
            .observe(this) {
                Log.d(TAG, "workInfo:${it[0]}")
            }
    }

    /**
     * 唯一任务
     */
    private fun startUniqueTask() {
        val uploadLogWorker =
            PeriodicWorkRequest.Builder(UploadLogWorker::class.java, 24, TimeUnit.HOURS)
                .build()

        // 如果出现重复任务,旧任务会保持,新任务会被抛弃
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "uploadLogs",
                ExistingPeriodicWorkPolicy.KEEP,
                uploadLogWorker
            )
    }

    /**
     * 任务链
     */
    private fun startChainTask(){
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val uploadLogWorker = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
            .setConstraints(constraints)
            .setInitialDelay(10,TimeUnit.SECONDS)
            .build()

        val compressLogWorker = OneTimeWorkRequest.Builder(CompressLogWorker::class.java)
            .setConstraints(constraints)
            .setInitialDelay(10,TimeUnit.SECONDS)
            .build()

        // 定义了2个任务链
        val workContinuationOne = WorkManager.getInstance(this)
            .beginWith(compressLogWorker)
            .then(uploadLogWorker)

        val workContinuationTwo = WorkManager.getInstance(this)
            .beginWith(compressLogWorker)
            .then(uploadLogWorker)

        val taskList = arrayListOf<WorkContinuation>(workContinuationOne,workContinuationTwo)
        WorkContinuation.combine(taskList)
            // 等2个任务链完成后再运行
            .then(uploadLogWorker)
            .enqueue()
    }

    /**
     * 取消任务
     */
    private fun cancelWork(){
        WorkManager.getInstance(this).cancelAllWork()
    }
}