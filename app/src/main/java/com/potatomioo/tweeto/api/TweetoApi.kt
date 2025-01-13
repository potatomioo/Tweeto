package com.potatomioo.tweeto.api

import com.potatomioo.tweeto.models.tweetsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetoApi {

    //two ways of handling headers notations

    @GET("https://api.jsonbin.io/v3/b/6784e9b7acd3cb34a8cadbbc?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category : String) : Response<List<tweetsItem>>


    @GET("https://api.jsonbin.io/v3/b/6784e9b7acd3cb34a8cadbbc?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories():Response<List<String>>
}