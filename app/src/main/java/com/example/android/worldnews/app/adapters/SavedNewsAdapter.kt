package com.example.android.worldnews.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.worldnews.app.model.Article
import com.example.android.worldnews.databinding.SavedListItemArticleBinding

class SavedNewsAdapter(val context:Context) : RecyclerView.Adapter<SavedNewsAdapter.MyViewHolder>() {
   inner  class MyViewHolder(val binding: SavedListItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback=object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem==newItem
        }

    }

    var differ=AsyncListDiffer(this,differCallback)
    var mListener:((Article)->Unit)?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SavedListItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.binding.apply {
            savedArticleTitle.text=article.title
            savedArticleAuthor.text=article.author
            savedArticleDescription.text=article.description
            Glide.with(context).load(article.urlToImage).into(savedArticleImage)
            root.setOnClickListener {
                mListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnClickListener(listener:(Article)->Unit){
        mListener=listener
    }


}