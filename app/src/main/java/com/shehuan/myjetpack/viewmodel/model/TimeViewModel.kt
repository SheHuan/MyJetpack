package com.shehuan.myjetpack.viewmodel.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class TimeViewModel : ViewModel() {
    val liveData: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }
}