package com.shehuan.myjetpack.room

import androidx.room.ColumnInfo

data class NamePrice(@ColumnInfo(name = "name") val name: String, @ColumnInfo(name = "price") val price: Float) {
    override fun toString(): String {
        return "NamePrice(name='$name', price=$price)"
    }
}