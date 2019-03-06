package com.shehuan.myjetpack.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shehuan.myjetpack.R
import com.shehuan.myjetpack.viewmodel.model.TimeViewModel
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {

    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, ViewModelActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        shareBtn.setOnClickListener {
            ShareActivity.start(this)
        }

        val viewModel = ViewModelProviders.of(this).get(TimeViewModel::class.java)
        timeBtn.setOnClickListener {
            viewModel.liveData.postValue(System.currentTimeMillis())
        }
        
        viewModel.liveData.observe(this, Observer<Long> {
            timeTv.text = it.toString()
        })
    }
}
