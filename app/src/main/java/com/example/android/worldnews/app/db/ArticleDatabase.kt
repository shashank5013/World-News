package com.example.android.worldnews.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.worldnews.app.model.Article
import com.example.android.worldnews.app.util.Constants.Companion.DATABASE_NAME


@TypeConverters(Converters::class)
@Database(entities = [Article::class],version = 1,exportSchema = false)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao():ArticleDao

    companion object{
        @Volatile
        private var instance:ArticleDatabase?=null

        fun getDatabase(context: Context):ArticleDatabase{
            if(instance!=null){
                return instance as ArticleDatabase
            }

            synchronized(this){
                return Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
        }
    }
}