package com.shehuan.myjetpack.paging.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shehuan.myjetpack.paging.database.ioExecute


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticleDb : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: ArticleDb? = null

        fun getInstance(context: Context): ArticleDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ArticleDb {
            return Room.databaseBuilder(context, ArticleDb::class.java, "article-db")
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        ioExecute {
                            getInstance(context)
                                .articleDao()
                                .addArticles(getArticleTitles().map {
                                    Article(0, it)
                                })
                        }
                    }
                })
                .build()
        }

        private fun getArticleTitles(): List<String> {
            val list = mutableListOf<String>()
            for (i: Int in 1..100) {
                list.add("Article--$i")
            }
            return list
        }
    }
}
