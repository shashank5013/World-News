package com.example.android.worldnews.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.android.worldnews.app.model.Article
import com.example.android.worldnews.databinding.ListItemArticleBinding

class NewsAdapter(private val context:Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ListItemArticleBinding):RecyclerView.ViewHolder(binding.root)

    private  var mListener:((Article)->Unit)?=null

    private val differCallback=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }

    var differ=AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ListItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article=differ.currentList[position]
        if(article.author==null){
            article.author="Unknown"
        }
        holder.binding.articleTitle.text=article.title
        holder.binding.articleDescription.text=article.description
        holder.binding.articleAuthor.text=article.source.name
        holder.binding.articleTime.text=article.publishedAt
        Glide.with(context).
        load(article.urlToImage).
        thumbnail(0.05f).
        transition(DrawableTransitionOptions.withCrossFade()).
        diskCacheStrategy(DiskCacheStrategy.NONE).
        into(holder.binding.articleImage)
        holder.binding.root.setOnClickListener{
            mListener?.let{ it(article) }
        }
        
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnClickListener(listener:(Article)->Unit){
        mListener=listener
    }

}