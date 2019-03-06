package com.shehuan.myjetpack.paging.database.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shehuan.myjetpack.R
import com.shehuan.myjetpack.Utils
import com.shehuan.myjetpack.paging.database.db.Article

class ArticleAdapter : PagedListAdapter<Article, ArticleAdapter.ViewHolder>(
    ArticleDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            Utils.inflate(
                parent.context,
                R.layout.rv_item_article_layout,
                parent
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTv = itemView.findViewById<TextView>(R.id.titleTv)

        fun bind(article: Article?) {
            titleTv.text = article?.title
        }
    }
}