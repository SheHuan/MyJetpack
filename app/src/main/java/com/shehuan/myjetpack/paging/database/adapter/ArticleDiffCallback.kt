package com.shehuan.myjetpack.paging.database.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shehuan.myjetpack.paging.database.db.Article


class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}