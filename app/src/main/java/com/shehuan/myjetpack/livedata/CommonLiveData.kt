package com.shehuan.myjetpack.livedata

import androidx.lifecycle.LiveData

class CommonLiveData(symbol: String) : LiveData<String>() {
    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }

    companion object {
        private lateinit var instance: CommonLiveData

        fun get(symbol: String): CommonLiveData {
            return if (::instance.isInitialized) instance else CommonLiveData(symbol)
        }
    }
}