package com.potatomioo.tweeto.di

import com.potatomioo.tweeto.api.TweetoApi
import com.potatomioo.tweeto.models.tweetsItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TweetModule{


    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTweets(retrofit: Retrofit) : TweetoApi{
        return retrofit.create(TweetoApi::class.java)
    }

}