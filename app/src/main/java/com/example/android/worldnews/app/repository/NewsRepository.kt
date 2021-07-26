package com.example.android.worldnews.app.repository

import com.example.android.worldnews.app.api.RetrofitInstance
import com.example.android.worldnews.app.db.ArticleDao
import com.example.android.worldnews.app.model.Article

class NewsRepository(val articleDao: ArticleDao) {

    suspend fun getBreakingNewsFromInternet()=RetrofitInstance.api.getBreakingNews(pageNumber = 1)

    suspend fun searchNews(q:String)=RetrofitInstance.api.searchForNews(q,1)

    suspend fun addOrUpdateArticle(article: Article)=articleDao.upsert(article)

    suspend fun deleteArticle(article: Article)=articleDao.deleteArticle(article)

    fun getArticlesFromDatabase()=articleDao.getAllArticles()
}