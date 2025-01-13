package com.potatomioo.tweeto.di

import com.potatomioo.tweeto.api.TweetoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    //Creating retrofit objects by hilt

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Creating API objects by for Retrofit

    @Singleton
    @Provides
    fun provideTweetoApi(retrofit: Retrofit) : TweetoApi{
        return retrofit.create(TweetoApi::class.java)
    }

}