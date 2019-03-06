package com.shehuan.myjetpack.viewmodel.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class IntViewModel : ViewModel() {
    val liveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}