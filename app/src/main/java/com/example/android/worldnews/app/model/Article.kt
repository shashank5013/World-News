package com.example.android.worldnews.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.worldnews.app.util.Constants.Companion.TABLE_NAME
import org.intellij.lang.annotations.PrintFormat


@Entity(tableName = TABLE_NAME)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)