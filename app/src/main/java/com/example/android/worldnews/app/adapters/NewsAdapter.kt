package com.example.android.worldnews.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.worldnews.app.model.Article
import com.example.android.worldnews.databinding.ListItemArticleBinding

class NewsAdapter(private val context:Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ListItemArticleBinding):RecyclerView.ViewHolder(binding.root)

    private var articles= emptyList<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ListItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article=articles[position]
        holder.binding.articleTitle.text=article.title
        holder.binding.articleDescription.text=article.description
        holder.binding.articleAuthor.text=article.source.name
        holder.binding.articleTime.text=article.publishedAt
        Glide.with(context).load(article.urlToImage).into(holder.binding.articleImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setData(data:List<Article>){
        articles=data
        notifyDataSetChanged()
    }

}