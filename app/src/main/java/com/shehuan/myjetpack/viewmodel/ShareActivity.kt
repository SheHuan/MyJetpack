package com.shehuan.myjetpack.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shehuan.myjetpack.R

class ShareActivity : AppCompatActivity() {

    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, ShareActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
    }
}
