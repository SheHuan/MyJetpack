package com.shehuan.myjetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Result
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.concurrent.thread


class BusyWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        thread {
            Log.e("BusyWorker", "任务开始执行")
            Thread.sleep(2000)
            Log.e("BusyWorker", "任务执行结束")
        }
        return Result.success()
    }
}