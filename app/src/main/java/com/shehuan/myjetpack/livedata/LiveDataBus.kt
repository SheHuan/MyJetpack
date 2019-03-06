package com.shehuan.myjetpack.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

object LiveDataBus {

    // 为每个 LiveData 指定一个 key，并保存对应关系
    private val liveDataMap = mutableMapOf<String, MutableLiveData<*>>()

    /**
     * 可以在主线程或子线程发送数据
     */
    fun <T> postValue(liveDataKey: String, value: T) {
        prepareLiveData<T>(liveDataKey).postValue(value)
    }

    /**
     * 建立订阅关系
     */
    fun <T> observe(liveDataKey: String, owner: LifecycleOwner, observer: Observer<T>) {
        prepareLiveData<T>(liveDataKey).observe(owner, observer)
    }

    /**
     * 得到 liveDataKey 对应的 LiveData
     */
    private fun <T> prepareLiveData(liveDataKey: String): MutableLiveData<T> {
        val liveData: MutableLiveData<T>
        if (liveDataMap.containsKey(liveDataKey)) {
            liveData = liveDataMap[liveDataKey] as MutableLiveData<T>
        } else {
            liveData = MutableLiveData()
            liveDataMap[liveDataKey] = liveData
        }
        return liveData
    }

    /**
     * 释放全部 LiveData
     */
    fun destroy() {
        liveDataMap.clear()
    }

    /**
     * 释放指定 LiveData
     */
    fun destory(liveDataKey: String) {
        liveDataMap.remove(liveDataKey)
    }
}