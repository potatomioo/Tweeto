package com.potatomioo.tweeto.api

import com.potatomioo.tweeto.models.tweetsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetoApi {

    @GET("v3/b/679096b3e41b4d34e47cb8c7?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path")category : String) : Response<List<tweetsItem>>

    @GET("v3/b/679096b3e41b4d34e47cb8c7?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCateories() : Response<List<String>>

}