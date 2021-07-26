package com.example.android.worldnews.app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.worldnews.app.util.Constants.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize
import org.intellij.lang.annotations.PrintFormat
import java.io.Serializable


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
) : Serializable