package com.example.android.worldnews.app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.worldnews.app.api.RetrofitInstance
import com.example.android.worldnews.app.model.NewsResponse
import com.example.android.worldnews.app.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    var data=MutableLiveData<NewsResponse>()
    private val repository:NewsRepository = NewsRepository()

    init {
        viewModelScope.launch (Dispatchers.IO){
            val temp=RetrofitInstance.api.getBreakingNews(countryCode = "in",pageNumber = 1).body()!!
            data.postValue(temp)
        }

    }
}