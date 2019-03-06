package com.shehuan.myjetpack.lifecycles

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.shehuan.myjetpack.R

class LifecycleOwnerActivity : Activity(), LifecycleOwner {
    companion object {
        fun start(context: Activity) {
            val intent = Intent(context, LifecycleOwnerActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var mLifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_owner)
        mLifecycleRegistry = LifecycleRegistry(this)
        mLifecycleRegistry.markState(Lifecycle.State.CREATED)

        test()
    }

    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    private fun test(){
        val playing = Playing(this, lifecycle)
        // 让Playing类观察LifecycleOwnerActivity类的生命周期
        lifecycle.addObserver(playing)
    }
}
