package com.shehuan.myjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.shehuan.myjetpack.lifecycles.LifecycleOwnerActivity
import com.shehuan.myjetpack.livedata.LiveDataActivity
import com.shehuan.myjetpack.livedata.LiveDataBus
import com.shehuan.myjetpack.navigation.NavigationActivity
import com.shehuan.myjetpack.paging.PagingActivity
import com.shehuan.myjetpack.room.RoomActivity
import com.shehuan.myjetpack.viewmodel.ViewModelActivity
import com.shehuan.myjetpack.workmanager.WorkManagerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LiveDataBus.observe("msg1", this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun lifecycle(view: View) {
        LifecycleOwnerActivity.start(this)
    }

    fun liveData(view: View) {
        LiveDataActivity.start(this)
    }

    fun viewModel(view: View) {
        ViewModelActivity.start(this)
    }

    fun room(view: View) {
        RoomActivity.start(this)
    }

    fun paging(view: View) {
        PagingActivity.start(this)
    }

    fun workManager(view: View) {
        WorkManagerActivity.start(this)
    }

    fun navigation(view: View) {
        NavigationActivity.start(this)
    }
}
