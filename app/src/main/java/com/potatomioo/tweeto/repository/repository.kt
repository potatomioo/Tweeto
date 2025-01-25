package com.potatomioo.tweeto.repository

import com.potatomioo.tweeto.api.TweetoApi
import com.potatomioo.tweeto.models.tweetsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Thread.State
import javax.inject.Inject

class repository @Inject constructor(private val tweetoApi: TweetoApi){

    //State flow for the data changes which will be observed by observable
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories : StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<tweetsItem>>(emptyList())
    val tweets : StateFlow<List<tweetsItem>>
        get() = _tweets

    suspend fun getCategories(){
        val response = tweetoApi.getCateories()
        if(response.isSuccessful && response.body() != null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category : String){
        val response = tweetoApi.getTweets(category)
        if(response.isSuccessful && response.body() != null){
            _tweets.emit(response.body()!!)
        }
    }
}