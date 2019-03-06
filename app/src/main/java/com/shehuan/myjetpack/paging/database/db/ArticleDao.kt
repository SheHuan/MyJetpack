package com.shehuan.myjetpack.paging.database.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAllArticles(): DataSource.Factory<Int, Article>

    @Insert
    fun addArticles(articles: List<Article>)

    @Insert
    fun addArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)
}