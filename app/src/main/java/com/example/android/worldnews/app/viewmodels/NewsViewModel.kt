package com.example.android.worldnews.app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.worldnews.app.db.ArticleDatabase
import com.example.android.worldnews.app.model.Article
import com.example.android.worldnews.app.model.NewsResponse
import com.example.android.worldnews.app.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    var dataFromInternet = MutableLiveData<NewsResponse>()
    var repository: NewsRepository


    init {
        val dao =ArticleDatabase.getDatabase(application).getArticleDao()
        repository= NewsRepository(dao)
    }

    fun getBreakingNews() = viewModelScope.launch(Dispatchers.IO) {
        val temp = repository.getBreakingNewsFromInternet()
        dataFromInternet.postValue(temp.body())
    }


    fun searchNews(q: String) = viewModelScope.launch(Dispatchers.IO) {
        val temp = repository.searchNews(q)
        dataFromInternet.postValue(temp.body())
    }

    fun addOrUpdateArticle(article: Article) = viewModelScope.launch (Dispatchers.IO){
        repository.addOrUpdateArticle(article)
    }

    fun getArticlesFromDatabase()=repository.getArticlesFromDatabase()


    fun deleteArticle(article: Article)=viewModelScope.launch (Dispatchers.IO){
        repository.deleteArticle(article)
    }

}