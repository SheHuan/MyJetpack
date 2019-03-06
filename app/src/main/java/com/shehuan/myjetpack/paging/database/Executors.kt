package com.shehuan.myjetpack.paging.database

import java.util.concurrent.Executors

private val EXECUTOR = Executors.newSingleThreadExecutor()

fun ioExecute(runnable: () -> Unit) {
    EXECUTOR.execute(runnable)
}