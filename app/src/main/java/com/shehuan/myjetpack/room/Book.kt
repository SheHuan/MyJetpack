package com.shehuan.myjetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shehuan.myjetpack.Utils
import java.util.*

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price: Float,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "publish_date") val publishDate: Date?
) {
    override fun toString(): String {
        return "Book(id=$id, name='$name', price=$price, author='$author', publishDate=${Utils.formatDate(publishDate?.time)})"
    }
}