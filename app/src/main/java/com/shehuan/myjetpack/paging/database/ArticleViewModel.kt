package com.shehuan.myjetpack.paging.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.shehuan.myjetpack.paging.database.db.Article
import com.shehuan.myjetpack.paging.database.db.ArticleDb

class ArticleViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = ArticleDb.getInstance(app).articleDao()

    val articles: LiveData<PagedList<Article>> = LivePagedListBuilder(
        dao.getAllArticles(),
        PagedList.Config.Builder()
            .setInitialLoadSizeHint(15) // 第一加载的数据量
            .setPrefetchDistance(2) // 距离数据尾部还有几条数据时，开始加载下一页数据
            .setPageSize(15) // 每一页加载的数据量
            .setEnablePlaceholders(false) // 是否使用占位符
            .build()
    ).build()
}