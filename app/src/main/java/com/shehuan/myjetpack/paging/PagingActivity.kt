package com.shehuan.myjetpack.paging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shehuan.myjetpack.R
import com.shehuan.myjetpack.paging.database.ArticleViewModel
import com.shehuan.myjetpack.paging.database.adapter.ArticleAdapter
import com.shehuan.myjetpack.paging.database.db.Article
import com.shehuan.myjetpack.paging.database.db.ArticleDb
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {
    private lateinit var adapter: ArticleAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ArticleViewModel(application) as T
            }
        }).get(ArticleViewModel::class.java)
    }

    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, PagingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        adapter = ArticleAdapter()
        articleRv.layoutManager = LinearLayoutManager(this)
        articleRv.adapter = adapter

        viewModel.articles.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    fun addArticle(view: View) {
        ArticleDb.getInstance(this).articleDao().addArticle(
            Article(
                0, System.currentTimeMillis().toString()
            )
        )
    }
}
