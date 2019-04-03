package com.shehuan.myjetpack.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shehuan.myjetpack.R
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlin.concurrent.thread


class LiveDataActivity : AppCompatActivity() {
    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, LiveDataActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val liveData = MutableLiveData<Long>()
        liveData.postValue(System.currentTimeMillis())
        liveData.observe(this, Observer<Long> {
            msgTv1.text = it.toString()
        })


        val userLiveData = MutableLiveData<User>()
        Transformations.map(userLiveData) {
            "${it.name},${it.age}"
        }.observe(this, Observer<String> {
            msgTv2.text = it
        })
        userLiveData.postValue(User(System.currentTimeMillis(), "Tom", 18))


        LiveDataBus.postValue("msg", "Hello Kotlin")
        LiveDataBus.observe("msg", this, Observer<String> {
            msgTv3.text = it
        })

        thread {
            LiveDataBus.postValue("msg1", "Hello World")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
