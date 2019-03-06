package com.shehuan.myjetpack

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun formatDate(timestamp: Long?): String? {
            return timestamp?.let {
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = Date(it)
                sdf.format(date)
            }
        }

        fun inflate(context: Context, @LayoutRes layoutId: Int, parent: ViewGroup): View {
            return LayoutInflater.from(context).inflate(layoutId, parent, false)
        }
    }
}