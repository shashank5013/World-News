package com.example.android.worldnews.app.repository

import androidx.lifecycle.LiveData
import com.example.android.worldnews.app.api.RetrofitInstance
import com.example.android.worldnews.app.db.ArticleDao
import com.example.android.worldnews.app.model.Article
import com.example.android.worldnews.app.model.NewsResponse

class NewsRepository() {

    suspend fun getBreakingNewsFromInternet()=RetrofitInstance.api.getBreakingNews(pageNumber = 1)
}