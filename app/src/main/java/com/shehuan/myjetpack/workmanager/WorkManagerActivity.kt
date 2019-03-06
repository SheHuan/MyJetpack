package com.shehuan.myjetpack.workmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.shehuan.myjetpack.R

class WorkManagerActivity : AppCompatActivity() {

    companion object {
        fun start(context: Activity) {
            val intent = Intent(context, WorkManagerActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        val busyWorker = OneTimeWorkRequest.Builder(BusyWorker::class.java)
            .addTag("busy")
            .build()

        // 检查任务是否结束
        WorkManager.getInstance().getWorkInfoByIdLiveData(busyWorker.id).observe(this, Observer {
            if (it.state.isFinished) {
                Log.e("WorkInfo", "任务结束")
            } else {
                Log.e("WorkInfo", "任务未结束")
            }
        })

        // 开始执行任务
        WorkManager.getInstance().enqueue(busyWorker)
        // 取消任务
        WorkManager.getInstance().cancelWorkById(busyWorker.id)
    }
}
