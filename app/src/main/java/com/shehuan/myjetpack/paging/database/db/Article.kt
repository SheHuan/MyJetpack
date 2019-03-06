package com.shehuan.myjetpack.paging.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(@PrimaryKey(autoGenerate = true) val id: Int, val title: String)